package com.example.mealio.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.mealio.model.MealRepository;
import com.example.mealio.model.pojo.MealsResponse;
import com.example.mealio.view.mainScreen.Home.getMealByCategory.MealByCategoryView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealByCategoryPresenter {
    private MealByCategoryView mealView;
    private MealRepository mealRepository;


    public MealByCategoryPresenter(MealByCategoryView mealView, MealRepository mealRepository){
        this.mealView = mealView;
        this.mealRepository = mealRepository;
    }


    @SuppressLint("CheckResult")
    public void getMealByCategory(String categoryId){
        Single<MealsResponse> filterByCategory = mealRepository.filterByCategory(categoryId);
        filterByCategory.subscribeOn(Schedulers.io())
                .map(item -> item.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            mealView.setMealsByCategory(list);
                            Log.i("TAG", list.size() + "");
                        },
                        error -> {
                            mealView.setErrorMessage(error.getMessage());
                        }
                );
    }


}


