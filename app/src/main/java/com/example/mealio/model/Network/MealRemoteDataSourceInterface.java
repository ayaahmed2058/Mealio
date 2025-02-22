package com.example.mealio.model.Network;

public interface MealRemoteDataSourceInterface {

    public void makeNetworkCallBackForRandomMeal (NetworkCallBack networkCallBack);
    public void makeNetworkCallBackForAllAreas (AreaNetworkCallBack areaNetworkCallBack);
    public void makeNetworkCallBackForAllCategories (CategoryNetworkCallBack categoryNetworkCallBack);
    public void makeNetworkCallBackForAllIngredients (NetworkCallBack networkCallBack);
}
