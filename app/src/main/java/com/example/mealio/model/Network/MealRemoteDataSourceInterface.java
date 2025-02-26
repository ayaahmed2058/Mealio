package com.example.mealio.model.Network;

import com.example.mealio.model.pojo.MealResponse;
import com.example.mealio.model.pojo.MealsResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;

public interface MealRemoteDataSourceInterface {

    void makeNetworkCallBackForRandomMeal (MealNetworkCallBack mealNetworkCallBack);
    void makeNetworkCallBackForAllAreas (AreaNetworkCallBack areaNetworkCallBack);
    void makeNetworkCallBackForAllCategories (CategoryNetworkCallBack categoryNetworkCallBack);
    void makeNetworkCallBackForAllIngredient (IngredientNetworkCallBack ingredientNetworkCallBack);
    void makeNetworkCallBackForMealDetails (MealDetailsNetworkCallBack mealDetailsNetworkCallBack, String mealID);

    Observable<MealsResponse> filterByCategoryNetworkCallBack (String categoryID);
    Observable<MealsResponse> filterByAreaNetworkCallBack (String areaID);
    Observable<MealsResponse> filterByIngredientNetworkCallBack (String ingredientID);
    Observable<MealsResponse> searchForMeal (String mealName);
}
