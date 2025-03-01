package com.example.mealio.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.mealio.model.MealRepository;
import com.example.mealio.model.db.Meal;
import com.example.mealio.view.mainScreen.star.StarMealDetailsView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StarMealDetailsPresenter {
    private MealRepository mealRepository;
    private StarMealDetailsView starMealDetailsView;

    public StarMealDetailsPresenter (StarMealDetailsView starMealDetailsView, MealRepository mealRepository){
        this.mealRepository = mealRepository;
        this.starMealDetailsView = starMealDetailsView;
    }


    @SuppressLint("CheckResult")
    public void getMealDetailsById(String mealId) {
        Observable<Meal> mealObservable = mealRepository.getMealDetailsById(mealId);
        mealObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal -> {
                    starMealDetailsView.setMealDetails(meal);
                }, error -> {
                    starMealDetailsView.setErrorMessage(error.getMessage());
                });
    }

}
