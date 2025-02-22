package com.example.mealio.model.Network;

public interface MealRemoteDataSourceInterface {

    public void makeNetworkCallBackForRandomMeal (MealNetworkCallBack mealNetworkCallBack);
    public void makeNetworkCallBackForAllAreas (AreaNetworkCallBack areaNetworkCallBack);
    public void makeNetworkCallBackForAllCategories (CategoryNetworkCallBack categoryNetworkCallBack);
}
