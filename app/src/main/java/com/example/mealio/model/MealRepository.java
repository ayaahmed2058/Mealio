package com.example.mealio.model;


import com.example.mealio.model.Network.AreaNetworkCallBack;
import com.example.mealio.model.Network.CategoryNetworkCallBack;
import com.example.mealio.model.Network.IngredientNetworkCallBack;
import com.example.mealio.model.Network.MealDetailsNetworkCallBack;
import com.example.mealio.model.Network.MealRemoteDataSourceInterface;
import com.example.mealio.model.Network.MealNetworkCallBack;
import com.example.mealio.model.db.Meal;
import com.example.mealio.model.db.MealLocalDataSourceInterface;
import com.example.mealio.model.db.WeekPlanner;
import com.example.mealio.model.pojo.MealSummary;
import com.example.mealio.model.pojo.MealsResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;


public class MealRepository {
    private MealRemoteDataSourceInterface mealRemoteDataSourceInterface;
    private MealLocalDataSourceInterface mealLocalDataSourceInterface;
    private static MealRepository instance = null;

    private MealRepository(MealLocalDataSourceInterface mealLocalDataSourceInterface, MealRemoteDataSourceInterface mealRemoteDataSourceInterface) {
        this.mealLocalDataSourceInterface = mealLocalDataSourceInterface;
        this.mealRemoteDataSourceInterface = mealRemoteDataSourceInterface;
    }

    public static MealRepository getInstance (MealLocalDataSourceInterface mealLocalDataSourceInterface, MealRemoteDataSourceInterface mealRemoteDataSourceInterface){
        if(instance == null){
            instance = new MealRepository(mealLocalDataSourceInterface, mealRemoteDataSourceInterface);
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

    public void getAllIngredient(IngredientNetworkCallBack ingredientNetworkCallBack){
        mealRemoteDataSourceInterface.makeNetworkCallBackForAllIngredient(ingredientNetworkCallBack);
    }

    public void getMealDetails (MealDetailsNetworkCallBack mealDetailsNetworkCallBack, String mealID){
        mealRemoteDataSourceInterface.makeNetworkCallBackForMealDetails(mealDetailsNetworkCallBack , mealID);
    }

    public Observable<MealsResponse> filterByArea(String areaID){
        return mealRemoteDataSourceInterface.filterByAreaNetworkCallBack(areaID);
    }
    public Observable<MealsResponse> filterByCategory(String categoryID){
        return mealRemoteDataSourceInterface.filterByCategoryNetworkCallBack(categoryID);
    }
    public Observable<MealsResponse> filterByIngredient(String ingredientID){
        return mealRemoteDataSourceInterface.filterByIngredientNetworkCallBack(ingredientID);
    }

    public Observable<MealsResponse> searchMeals(String mealName) {
        return mealRemoteDataSourceInterface.searchForMeal(mealName);
    }


    public Observable<List<Meal>> getStoredMeal (){
        return mealLocalDataSourceInterface.getStarsMeal();
    }

    public Completable insertMeal (Meal meal){
        return mealLocalDataSourceInterface.addMeal(meal);
    }

    public Completable deleteMeal (Meal meal){
        return mealLocalDataSourceInterface.deleteMeal(meal);
    }

    public Observable<List<WeekPlanner>> getStoredPlanningMeals (){
        return mealLocalDataSourceInterface.getPlanningMeals();
    }

    public Completable insertPlanningMeal (WeekPlanner meal){
        return mealLocalDataSourceInterface.addPlanningMeal(meal);
    }

    public Completable deletePlanningMeal (WeekPlanner meal){
        return mealLocalDataSourceInterface.deletePlaningMeal(meal);
    }



}
