package com.example.mealio.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.MealDetailsNetworkCallBack;
import com.example.mealio.model.db.Meal;
import com.example.mealio.model.db.WeekPlanner;
import com.example.mealio.view.mealDetails.MealDetailsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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

    @SuppressLint("CheckResult")
    public void addToStar (Meal meal){
        Completable completable =  mealRepository.insertMeal(meal);
        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> Log.i("TAG", "Meal Added Successfully"),
                        error -> {
                            mealDetailsView.setErrorMessage(error.getMessage());
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void addToWeekPlan (WeekPlanner weekPlanner){
        Completable completable =  mealRepository.insertPlanningMeal(weekPlanner);
        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> Log.i("TAG", "Meal Added Successfully to PLan"),
                        error -> {
                            mealDetailsView.setErrorMessage(error.getMessage());
                        }
                );
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
