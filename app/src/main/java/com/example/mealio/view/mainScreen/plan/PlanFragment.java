package com.example.mealio.view.mainScreen.plan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.db.MealLocalDataSourceImp;
import com.example.mealio.model.db.WeekPlanner;
import com.example.mealio.presenter.StarPresenter;
import com.example.mealio.presenter.WeekPlanPresenter;
import com.example.mealio.view.mainScreen.star.StarMealAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class PlanFragment extends Fragment implements PlanView, OnDeletePlanClickListener {
    private RecyclerView planMealsRecyclerView;
    private PlanMealAdapter planMealAdapter;
    private WeekPlanPresenter weekPlanPresenter;

    public PlanFragment() {
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
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        planMealsRecyclerView  = view.findViewById(R.id.recycler_PlanMeals);
        planMealAdapter = new PlanMealAdapter(this);
        weekPlanPresenter = new WeekPlanPresenter(this, MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                MealRemoteDataSourceImp.getInstance()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        planMealsRecyclerView.setLayoutManager(linearLayoutManager);
        planMealsRecyclerView.setAdapter(planMealAdapter);

        weekPlanPresenter.getPlannedMeal();
    }

    @Override
    public void deleteFromPlan(WeekPlanner meal) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this meal?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    weekPlanPresenter.deleteFromPlan(meal);
                    planMealAdapter.notifyDataSetChanged();
                    Snackbar.make(planMealsRecyclerView,"Meal Was deleted successfully From Plan" , Snackbar.LENGTH_LONG).show();;
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
        }

    @Override
    public void setMeals(List<WeekPlanner> meals) {
        planMealAdapter.updateData(meals);
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        Snackbar.make(planMealsRecyclerView,errorMessage , Snackbar.LENGTH_LONG).show();;
    }
}