package com.example.mealio.presenter;


import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealNetworkCallBack;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.view.mainScreen.Home.MealView;

import java.util.List;

public class RandomMealPresenter implements MealNetworkCallBack{

    private MealView mealView;
    private MealRepository mealRepository;


    public RandomMealPresenter(MealView mealView, MealRepository mealRepository){
        this.mealView = mealView;
        this.mealRepository = mealRepository;
    }

    public void getRandomMeal(){
        mealRepository.getRandomMeal(this);
    }


    @Override
    public void onSuccessResult(List<MealSummary> meals) {
        mealView.setMeals(meals);
    }

    @Override
    public void onFailureResult(String errorMessage) {
        mealView.setErrorMessage(errorMessage);
    }
}
