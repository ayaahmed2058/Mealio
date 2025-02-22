package com.example.mealio.model;


import com.example.mealio.model.Network.AreaNetworkCallBack;
import com.example.mealio.model.Network.CategoryNetworkCallBack;
import com.example.mealio.model.Network.MealRemoteDataSourceInterface;
import com.example.mealio.model.Network.MealNetworkCallBack;


public class MealRepository {
    private MealRemoteDataSourceInterface mealRemoteDataSourceInterface;
    private static MealRepository instance = null;

    private MealRepository(MealRemoteDataSourceInterface mealRemoteDataSourceInterface) {
        this.mealRemoteDataSourceInterface = mealRemoteDataSourceInterface;
    }

    public static MealRepository getInstance (MealRemoteDataSourceInterface mealRemoteDataSourceInterface){
        if(instance == null){
            instance = new MealRepository( mealRemoteDataSourceInterface);
        }
        return  instance;
    }


    public void getRandomMeal (MealNetworkCallBack mealNetworkCallBack){
        mealRemoteDataSourceInterface.makeNetworkCallBackForRandomMeal(mealNetworkCallBack);
    }

    public void getALLAreas (AreaNetworkCallBack areaNetworkCallBack){
        mealRemoteDataSourceInterface.makeNetworkCallBackForAllAreas(areaNetworkCallBack);
    }

    public void getAllCategories (CategoryNetworkCallBack categoryNetworkCallBack){
        mealRemoteDataSourceInterface.makeNetworkCallBackForAllCategories(categoryNetworkCallBack);
    }


}
