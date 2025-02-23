package com.example.mealio.model.Network;

import com.example.mealio.model.pojo.Meal;

import java.util.List;

public interface MealDetailsNetworkCallBack {
    void onSuccessResultForMealDetails (List<Meal> mealList);
    void onFailureResult (String errorMessage);
}
