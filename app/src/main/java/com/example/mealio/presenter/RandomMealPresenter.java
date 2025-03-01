package com.example.mealio.presenter;


import android.annotation.SuppressLint;
import android.util.Log;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.pojo.MealsResponse;
import com.example.mealio.view.mainScreen.Home.MealView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RandomMealPresenter {

    private MealView mealView;
    private MealRepository mealRepository;


    public RandomMealPresenter(MealView mealView, MealRepository mealRepository){
        this.mealView = mealView;
        this.mealRepository = mealRepository;
    }

    @SuppressLint("CheckResult")
    public void getRandomMeal(){
        Single<MealsResponse> mealsResponseSingle = mealRepository.getRandomMeal();
        mealsResponseSingle.subscribeOn(Schedulers.io())
                .map(item -> item.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            mealView.setMeals(list);
                            Log.i("TAG", list.size() + "");
                        },
                        error -> {
                            mealView.setErrorMessage(error.getMessage());
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void getMealStartedWith_F(){

        Single<MealsResponse> mealsResponseSingle = mealRepository.getMealsStartsWith_A("f");
        mealsResponseSingle.subscribeOn(Schedulers.io())
                .map(item -> item.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            mealView.setMeals(list);
                            Log.i("TAG", list.size() + "");
                        },
                        error -> {
                            mealView.setErrorMessage(error.getMessage());
                        }
                );

    }


}
