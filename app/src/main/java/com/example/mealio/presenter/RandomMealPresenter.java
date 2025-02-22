package com.example.mealio.presenter;


import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.AreaNetworkCallBack;
import com.example.mealio.model.Network.NetworkCallBack;
import com.example.mealio.model.pojo.AreaListItem;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.view.mainScreen.AllMealView;
import com.example.mealio.view.mainScreen.Home.allAreas.AreaView;

import java.util.List;

public class RandomMealPresenter implements NetworkCallBack, AreaNetworkCallBack {

    private AllMealView allMealView;
    private MealRepository mealRepository;
    private AreaView areaView;

    public RandomMealPresenter(AllMealView allMealView, AreaView areaView, MealRepository mealRepository){
        this.allMealView = allMealView;
        this.areaView = areaView;
        this.mealRepository = mealRepository;
    }

    public void getRandomMeal(){
        mealRepository.getRandomMeal(this);
    }

    public void getAllAreas (){
        mealRepository.getALLAreas(this);
    }


    @Override
    public void onSuccessResult(List<MealSummary> meals) {
        allMealView.setMeals(meals);
    }

    @Override
    public void onSuccessResultForArea(List<AreaListItem> areaListItems) {
        areaView.setAreas(areaListItems);
    }

    @Override
    public void onFailureResult(String errorMessage) {
        allMealView.setErrorMessage(errorMessage);
    }
}
