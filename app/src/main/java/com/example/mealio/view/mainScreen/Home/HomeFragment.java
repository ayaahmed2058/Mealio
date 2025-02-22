package com.example.mealio.view.mainScreen.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.pojo.AreaListItem;
import com.example.mealio.model.pojo.Category;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.presenter.AllAreasPresenter;
import com.example.mealio.presenter.AllCategoryPresenter;
import com.example.mealio.presenter.RandomMealPresenter;
import com.example.mealio.view.mainScreen.AllMealView;
import com.example.mealio.view.mainScreen.Home.allAreas.AreaView;
import com.example.mealio.view.mainScreen.Home.allAreas.AreasAdapter;
import com.example.mealio.view.mainScreen.Home.allCategoriesView.CategoriesAdapter;
import com.example.mealio.view.mainScreen.Home.allCategoriesView.CategoryView;
import com.example.mealio.view.mainScreen.Home.randomMeal.RandomMealAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class HomeFragment extends Fragment implements AllMealView, AreaView, CategoryView {

    private RandomMealAdapter randomMealAdapter;
    private AreasAdapter areasAdapter;
    private CategoriesAdapter categoriesAdapter;

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

        RecyclerView randomRecyclerView = view.findViewById(R.id.recycler_randomMeal);
        RecyclerView areasRecyclerView = view.findViewById(R.id.recycler_Areas);
        RecyclerView categoryRecyclerView = view.findViewById(R.id.recycler_categories);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        randomMealAdapter = new RandomMealAdapter(getActivity(), new ArrayList<>() );
        areasAdapter = new AreasAdapter(getActivity(), new ArrayList<>() );
        categoriesAdapter = new CategoriesAdapter(getActivity(), new ArrayList<>());

        RandomMealPresenter randomMealPresenter = new RandomMealPresenter(this,
                MealRepository.getInstance(MealRemoteDataSourceImp.getInstance()));

        AllAreasPresenter allAreasPresenter = new AllAreasPresenter(this,
                MealRepository.getInstance(MealRemoteDataSourceImp.getInstance()));

        AllCategoryPresenter allCategoryPresenter = new AllCategoryPresenter(this,
                MealRepository.getInstance(MealRemoteDataSourceImp.getInstance()));


        randomRecyclerView.setLayoutManager(linearLayoutManager);
        randomRecyclerView.setAdapter(randomMealAdapter);
        areasRecyclerView.setAdapter(areasAdapter);
        categoryRecyclerView.setAdapter(categoriesAdapter);

        randomMealPresenter.getRandomMeal();
        allAreasPresenter.getAllAreas();
        allCategoryPresenter.getAllCategories();
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
    public void setErrorMessage(String errorMessage) {
        Toast.makeText(getActivity() , errorMessage , Toast.LENGTH_LONG).show();

    }
}