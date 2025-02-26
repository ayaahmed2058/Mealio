package com.example.mealio.view.mainScreen.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.db.MealLocalDataSourceImp;
import com.example.mealio.model.pojo.AreaListItem;
import com.example.mealio.model.pojo.Category;
import com.example.mealio.model.pojo.IngredientListItem;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.presenter.AllAreasPresenter;
import com.example.mealio.presenter.AllCategoryPresenter;
import com.example.mealio.presenter.AllIngredientPresenter;
import com.example.mealio.presenter.RandomMealPresenter;
import com.example.mealio.view.mainScreen.Home.allAreas.AreaView;
import com.example.mealio.view.mainScreen.Home.allAreas.AreasAdapter;
import com.example.mealio.view.mainScreen.Home.allCategoriesView.CategoriesAdapter;
import com.example.mealio.view.mainScreen.Home.allCategoriesView.CategoryView;
import com.example.mealio.view.mainScreen.Home.allIngredient.IngredientView;
import com.example.mealio.view.mainScreen.Home.allIngredient.IngredientsAdapter;
import com.example.mealio.view.mainScreen.Home.randomMeal.RandomMealAdapter;
import com.google.android.material.chip.Chip;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class HomeFragment extends Fragment implements MealView, AreaView, CategoryView, IngredientView, OnMealClickListener {

    private RandomMealAdapter randomMealAdapter;
    private AreasAdapter areasAdapter;
    private CategoriesAdapter categoriesAdapter;
    private IngredientsAdapter ingredientsAdapter;
    private RecyclerView areasRecyclerView;
    private RecyclerView categoryRecyclerView;
    private RecyclerView ingredientRecyclerView;
    private Chip chipArea, chipCategory,chipIngredient;

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chipArea = view.findViewById(R.id.chip_area);
        chipCategory = view.findViewById(R.id.chip_category);
        chipIngredient = view.findViewById(R.id.chip_ingredient);
        TextView tvUserName = view.findViewById(R.id.user_name);

        String userName = getString(R.string.hello) + " "+ FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        tvUserName.setText(userName);


        RecyclerView randomRecyclerView = view.findViewById(R.id.recycler_randomMeal);
        areasRecyclerView = view.findViewById(R.id.recycler_Areas);
        categoryRecyclerView = view.findViewById(R.id.recycler_categories);
        ingredientRecyclerView = view.findViewById(R.id.recycler_ingredients);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        randomMealAdapter = new RandomMealAdapter(getActivity(), new ArrayList<>() , this);
        areasAdapter = new AreasAdapter(getActivity(), new ArrayList<>() );
        categoriesAdapter = new CategoriesAdapter(getActivity(), new ArrayList<>());
        ingredientsAdapter = new IngredientsAdapter(getActivity(), new ArrayList<>());

        RandomMealPresenter randomMealPresenter = new RandomMealPresenter(this,
                MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                        MealRemoteDataSourceImp.getInstance()));

        AllAreasPresenter allAreasPresenter = new AllAreasPresenter(this,
                MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                        MealRemoteDataSourceImp.getInstance()));

        AllCategoryPresenter allCategoryPresenter = new AllCategoryPresenter(this,
                MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                        MealRemoteDataSourceImp.getInstance()));

        AllIngredientPresenter allIngredientPresenter = new AllIngredientPresenter(this,
                MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                        MealRemoteDataSourceImp.getInstance()));


        randomRecyclerView.setLayoutManager(linearLayoutManager);
        randomRecyclerView.setAdapter(randomMealAdapter);
        areasRecyclerView.setAdapter(areasAdapter);
        categoryRecyclerView.setAdapter(categoriesAdapter);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);

        randomMealPresenter.getRandomMeal();
        allAreasPresenter.getAllAreas();
        allCategoryPresenter.getAllCategories();
        allIngredientPresenter.getAllIngredient();

        setupFilterChips();

    }

    @Override
    public void setMeals(List<MealSummary> mealSummaries) {
        randomMealAdapter.updateData(mealSummaries);
        randomMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAreas(List<AreaListItem> areas) {
        areasAdapter.updateData(areas);
        areasAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCategories(List<Category> categories) {
        categoriesAdapter.updateData(categories);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setIngredient(List<IngredientListItem> ingredientListItems) {
        ingredientsAdapter.updateData(ingredientListItems);
        ingredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        Toast.makeText(getActivity() , errorMessage , Toast.LENGTH_LONG).show();

    }

    private void setupFilterChips(){
        areasRecyclerView.setVisibility(View.GONE);
        categoryRecyclerView.setVisibility(View.GONE);
        ingredientRecyclerView.setVisibility(View.GONE);

        chipArea.setOnClickListener(v -> {
            areasRecyclerView.setVisibility(View.VISIBLE);
            categoryRecyclerView.setVisibility(View.GONE);
            ingredientRecyclerView.setVisibility(View.GONE);
        });

        chipCategory.setOnClickListener(v -> {
            categoryRecyclerView.setVisibility(View.VISIBLE);
            areasRecyclerView.setVisibility(View.GONE);
            ingredientRecyclerView.setVisibility(View.GONE);
        });

        chipIngredient.setOnClickListener(v -> {
            ingredientRecyclerView.setVisibility(View.VISIBLE);
            areasRecyclerView.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.GONE);
        });
    }

    @Override
    public void OnMealClicked(String id) {

        HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action =
                HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(id, "HomeFragment", "DetailsFragment");
        Navigation.findNavController(chipArea).navigate(action);

        //Navigation.findNavController(chipArea).navigate(R.id.action_homeFragment_to_mealDetailsFragment);
    }
}