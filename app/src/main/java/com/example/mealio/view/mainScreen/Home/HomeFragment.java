package com.example.mealio.view.mainScreen.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.db.MealLocalDataSourceImp;
import com.example.mealio.model.pojo.Category;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.presenter.AllCategoryPresenter;
import com.example.mealio.presenter.MealByCategoryPresenter;
import com.example.mealio.presenter.RandomMealPresenter;
import com.example.mealio.view.mainScreen.Home.allCategories.CategoriesAdapter;
import com.example.mealio.view.mainScreen.Home.allCategories.CategoryClickListener;
import com.example.mealio.view.mainScreen.Home.allCategories.CategoryView;
import com.example.mealio.view.mainScreen.Home.getMealByCategory.GetMealByCategoryAdapter;
import com.example.mealio.view.mainScreen.Home.getMealByCategory.MealByCategoryView;
import com.example.mealio.view.mainScreen.Home.popularMeal.PopularMealAdapter;
import com.example.mealio.view.mainScreen.Home.randomMeal.RandomMealAdapter;
import com.example.mealio.view.mainScreen.Utils;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("NotifyDataSetChanged")
public class HomeFragment extends Fragment implements MealView, CategoryView, MealByCategoryView, OnMealClickListener, CategoryClickListener {

    private RandomMealAdapter randomMealAdapter;
    private CategoriesAdapter categoriesAdapter;
    private PopularMealAdapter popularMealAdapter;
    private RecyclerView categoryRecyclerView;
    private MealByCategoryPresenter getMealByCategory;
    private GetMealByCategoryAdapter getMealByCategoryAdapter;
    private ImageView internetConnection;
    private Group homeGroup;
    private FirebaseAuth firebaseAuth;



    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvUserName = view.findViewById(R.id.user_name);


        if (firebaseAuth.getCurrentUser() != null && firebaseAuth.getCurrentUser().isAnonymous()) {
            tvUserName.setText(getString(R.string.hello) + " Guest");
        } else {
            String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
            tvUserName.setText(getString(R.string.hello) + " " + userName);
        }


        internetConnection = view.findViewById(R.id.internetConnection);
        homeGroup = view.findViewById(R.id.home_group);

        checkInternetConnection();


        RecyclerView randomRecyclerView = view.findViewById(R.id.recycler_randomMeal);
        categoryRecyclerView = view.findViewById(R.id.recycler_categories);
        RecyclerView getMealByCategoryRecyclerView = view.findViewById(R.id.recycler_getMealByCategory);
        RecyclerView popularMealRecyclerView = view.findViewById(R.id.recycler_popularMeal);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        randomMealAdapter = new RandomMealAdapter(getActivity(), new ArrayList<>() , this);
        categoriesAdapter = new CategoriesAdapter(getActivity(), new ArrayList<>(),this);
        getMealByCategoryAdapter = new GetMealByCategoryAdapter(getActivity() , new ArrayList<>(),this);
        popularMealAdapter = new PopularMealAdapter(getActivity(),new ArrayList<>(),this);


        RandomMealPresenter randomMealPresenter = new RandomMealPresenter(this,
                MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                        MealRemoteDataSourceImp.getInstance()));

        AllCategoryPresenter allCategoryPresenter = new AllCategoryPresenter(this,
                MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                        MealRemoteDataSourceImp.getInstance()));

        getMealByCategory = new MealByCategoryPresenter(this,
                MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                        MealRemoteDataSourceImp.getInstance()));


        randomRecyclerView.setLayoutManager(linearLayoutManager);
        randomRecyclerView.setAdapter(randomMealAdapter);
        categoryRecyclerView.setAdapter(categoriesAdapter);
        getMealByCategoryRecyclerView.setAdapter(getMealByCategoryAdapter);
        popularMealRecyclerView.setAdapter(popularMealAdapter);


        randomMealPresenter.getRandomMeal();
        allCategoryPresenter.getAllCategories();
        randomMealPresenter.getMealStartedWith_F();

    }

    @Override
    public void setMeals(List<MealSummary> meals) {
        randomMealAdapter.updateData(meals);
        popularMealAdapter.updatePopularMeal(meals);
        randomMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCategories(List<Category> categories) {
        categoriesAdapter.updateData(categories);
        categoriesAdapter.notifyDataSetChanged();
    }


    @Override
    public void setMealsByCategory(List<MealSummary> mealSummaries) {
        getMealByCategoryAdapter.updateData(mealSummaries);
        getMealByCategoryAdapter.notifyDataSetChanged();
    }


    @Override
    public void setErrorMessage(String errorMessage) {
        Toast.makeText(getActivity() , errorMessage , Toast.LENGTH_LONG).show();

    }


    @Override
    public void OnMealClicked(String id) {

        if (Utils.isNetworkAvailable(requireContext())) {
            HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action =
                    HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(id, "HomeFragment","MealDetailsFragment");
            Navigation.findNavController(categoryRecyclerView).navigate(action);

        } else {
            Snackbar.make(requireView(), "No internet Connection!", Toast.LENGTH_SHORT).show();
        }

        //Navigation.findNavController(chipArea).navigate(R.id.action_homeFragment_to_mealDetailsFragment);
    }

    @Override
    public void onCategoryClickListener(String categoryId) {
        getMealByCategory.getMealByCategory(categoryId);
        getMealByCategoryAdapter.notifyDataSetChanged();
    }

    private void checkInternetConnection() {
        if (Utils.isNetworkAvailable(requireContext())) {
            internetConnection.setVisibility(View.GONE);
            homeGroup.setVisibility(View.VISIBLE);
        } else {
            internetConnection.setVisibility(View.VISIBLE);
            homeGroup.setVisibility(View.GONE);
            Snackbar.make(requireView(), "No internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }

}