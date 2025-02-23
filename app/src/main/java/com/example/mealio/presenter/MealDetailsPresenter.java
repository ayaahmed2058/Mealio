package com.example.mealio.presenter;

import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealDetailsNetworkCallBack;
import com.example.mealio.model.pojo.IngredientListItem;
import com.example.mealio.model.pojo.Meal;
import com.example.mealio.view.mealDetails.MealDetailsView;

import java.util.List;

public class MealDetailsPresenter implements MealDetailsNetworkCallBack {

    private MealDetailsView mealDetailsView;
    private MealRepository mealRepository;


    public MealDetailsPresenter(MealDetailsView mealDetailsView, MealRepository mealRepository){
        this.mealDetailsView = mealDetailsView;
        this.mealRepository = mealRepository;
    }

    public void getMealDetails(String mealID){
        mealRepository.getMealDetails(this,mealID);
    }



    @Override
    public void onSuccessResultForMealDetails(List<Meal> mealList) {
        mealDetailsView.setMealDetails(mealList);
    }

    @Override
    public void onFailureResult(String errorMessage) {
        mealDetailsView.setErrorMessage(errorMessage);
    }
}
