package com.example.mealio.presenter;


import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealNetworkCallBack;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.view.mainScreen.Home.AllMealView;

import java.util.List;

public class RandomMealPresenter implements MealNetworkCallBack{

    private AllMealView allMealView;
    private MealRepository mealRepository;


    public RandomMealPresenter(AllMealView allMealView, MealRepository mealRepository){
        this.allMealView = allMealView;
        this.mealRepository = mealRepository;
    }

    public void getRandomMeal(){
        mealRepository.getRandomMeal(this);
    }


    @Override
    public void onSuccessResult(List<MealSummary> meals) {
        allMealView.setMeals(meals);
    }

    @Override
    public void onFailureResult(String errorMessage) {
        allMealView.setErrorMessage(errorMessage);
    }
}
