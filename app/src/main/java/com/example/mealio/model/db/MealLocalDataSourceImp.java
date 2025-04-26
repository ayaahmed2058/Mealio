package com.example.mealio.model.db;

import android.content.Context;

import com.example.mealio.model.pojo.MealSummary;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealLocalDataSourceImp implements MealLocalDataSourceInterface {
    private final MealDAO mealDAO;
    private final WeekPlanDAO weekPlanDAO;
    private static MealLocalDataSourceImp instance = null;


    private MealLocalDataSourceImp(Context context){
        AppDB appDB = AppDB.getInstance(context);
        mealDAO = appDB.getMealDAO();
        weekPlanDAO = appDB.getWeekPlanDAO();

    }

    public static MealLocalDataSourceImp getInstance(Context context){
        if(instance == null){
            instance = new MealLocalDataSourceImp(context);
        }
        return instance;
    }

    @Override
    public Completable addMeal(Meal meal){

        return mealDAO.insertMeal(meal);

    }

    @Override
    public Observable<List<Meal>> getStarsMeal(){
        return mealDAO.getStoredMeal();
    }

    @Override
    public  Completable deleteMeal(Meal meal){

        return mealDAO.deleteMeal(meal);

    }

    @Override
    public Observable<Meal> getMealById(String mealId) {
        return mealDAO.getMealById(mealId);
    }

    @Override
    public Completable addPlanningMeal(WeekPlanner meal) {
        return weekPlanDAO.insertPlanningMeal(meal);
    }

    @Override
    public Observable<List<WeekPlanner>> getPlanningMeals() {
        return weekPlanDAO.getStoredPlanningMeal();
    }

    @Override
    public Completable deletePlaningMeal(WeekPlanner meal) {
        return weekPlanDAO.deletePlanningMeal(meal);
    }

}
