package com.example.mealio.view.mainScreen.star;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.db.Meal;
import com.example.mealio.model.db.MealLocalDataSourceImp;
import com.example.mealio.presenter.MealDetailsPresenter;
import com.example.mealio.presenter.StarMealDetailsPresenter;
import com.example.mealio.presenter.StarPresenter;
import com.example.mealio.view.mealDetails.MealDetailsView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class StarMealDetailsFragment extends Fragment implements StarMealDetailsView {

    private RecyclerView starMealDetailsRecyclerView;
    private StarMealDetailsAdapter starMealDetailsAdapter;
    private StarMealDetailsPresenter starMealDetailsPresenter;

    public StarMealDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_star_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        starMealDetailsRecyclerView = view.findViewById(R.id.starMeal_details);

        String mealID = StarMealDetailsFragmentArgs.fromBundle(getArguments()).getStarMealID();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        starMealDetailsRecyclerView.setLayoutManager(layoutManager);

        starMealDetailsAdapter = new StarMealDetailsAdapter();
        starMealDetailsRecyclerView.setAdapter(starMealDetailsAdapter);

        starMealDetailsPresenter = new StarMealDetailsPresenter(this, MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                MealRemoteDataSourceImp.getInstance()));

        starMealDetailsPresenter.getMealDetailsById(mealID);
    }

    @Override
    public void setMealDetails(Meal meal) {
        List<Meal> mealList = new ArrayList<>();
        mealList.add(meal);
        starMealDetailsAdapter.updateData(mealList);
    }


    @Override
    public void setErrorMessage(String errorMessage) {
        Snackbar.make(starMealDetailsRecyclerView, errorMessage, Snackbar.LENGTH_LONG).show();
    }
}