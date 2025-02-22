package com.example.mealio.model.Network;

public interface MealRemoteDataSourceInterface {

    void makeNetworkCallBackForRandomMeal (MealNetworkCallBack mealNetworkCallBack);
    void makeNetworkCallBackForAllAreas (AreaNetworkCallBack areaNetworkCallBack);
    void makeNetworkCallBackForAllCategories (CategoryNetworkCallBack categoryNetworkCallBack);
    void makeNetworkCallBackForAllIngredient (IngredientNetworkCallBack ingredientNetworkCallBack);
}
