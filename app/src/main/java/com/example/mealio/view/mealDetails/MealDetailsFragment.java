package com.example.mealio.view.mealDetails;


import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mealio.R;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealRemoteDataSourceImp;
import com.example.mealio.model.db.Meal;
import com.example.mealio.model.db.MealLocalDataSourceImp;
import com.example.mealio.model.db.WeekPlanner;
import com.example.mealio.presenter.MealDetailsPresenter;
import com.example.mealio.view.mainScreen.Home.OnMealClickListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.List;


public class MealDetailsFragment extends Fragment implements MealDetailsView, OnStarClickListener, OnCalenderClickListener {
    private RecyclerView mealDetailsRecyclerView;
    private MealDetailsAdapter mealDetailsAdapter;
    private MealDetailsPresenter mealDetailsPresenter;

    public MealDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String mealID = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealID();
        String sourceScreen = MealDetailsFragmentArgs.fromBundle(getArguments()).getSearchMealID();
        String destinationScreen = MealDetailsFragmentArgs.fromBundle(getArguments()).getStarMealID();

        Log.d("MealDetailsFragment", "Navigated from: " + sourceScreen);


        mealDetailsRecyclerView = view.findViewById(R.id.meal_details);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mealDetailsRecyclerView.setLayoutManager(layoutManager);

        mealDetailsAdapter = new MealDetailsAdapter(this, this);
        mealDetailsRecyclerView.setAdapter(mealDetailsAdapter);

        mealDetailsPresenter = new MealDetailsPresenter(this, MealRepository.getInstance(MealLocalDataSourceImp.getInstance(getContext()),
                MealRemoteDataSourceImp.getInstance()));
        mealDetailsPresenter.getMealDetails(mealID);
    }

    @Override
    public void setMealDetails(List<Meal> meals) {
        for (Meal meal : meals) {
            meal.extractIngredients();
        }
        mealDetailsAdapter.updateData(meals);
        mealDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setErrorMessage(String errorMessage) {

    }

    @Override
    public void addToStar(Meal meal) {
        mealDetailsPresenter.addToStar(meal);
        mealDetailsAdapter.notifyDataSetChanged();
        Snackbar.make(mealDetailsRecyclerView, "Meal is Added successfully", Snackbar.LENGTH_LONG).show();
        ;

    }


    @Override
    public void OnCalenderClicked(String id, String mealName, String mealThumb) {
        showDatePicker(id,mealName,mealThumb);
    }
    private void showDatePicker(String mealId, String mealName , String mealThumb) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view, year1, month1, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
            showMealTypeDialog(mealId, mealName, mealThumb, selectedDate);
        }, year, month, day);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

    private void showMealTypeDialog(String mealId,String mealName , String mealThumb, String selectedDate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Choose The Meal Type");

        String[] mealTypes = {"Breakfast", "Lunch", "Dinner"};
        builder.setItems(mealTypes, (dialog, which) -> {
            String selectedMealType = mealTypes[which];
            WeekPlanner weekPlanner = new WeekPlanner(mealId,mealName,selectedDate,selectedMealType,mealThumb);
            mealDetailsPresenter.addToWeekPlan(weekPlanner);
            Snackbar.make(mealDetailsRecyclerView, "Meal AddedSuccessfully to Plan", Snackbar.LENGTH_LONG);
        });

        builder.show();
    }


}
