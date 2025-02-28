package com.example.mealio.presenter;

import android.annotation.SuppressLint;
import android.util.Log;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.db.Meal;
import com.example.mealio.model.db.WeekPlanner;
import com.example.mealio.model.pojo.MealResponse;
import com.example.mealio.view.mealDetails.MealDetailsView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenter{

    private MealDetailsView mealDetailsView;
    private MealRepository mealRepository;


    public MealDetailsPresenter(MealDetailsView mealDetailsView, MealRepository mealRepository){
        this.mealDetailsView = mealDetailsView;
        this.mealRepository = mealRepository;
    }

    @SuppressLint("CheckResult")
    public void getMealDetails(String mealID){
        Observable<MealResponse> mealResponseObservable = mealRepository.getMealDetails(mealID);
        mealResponseObservable.subscribeOn(Schedulers.io())
                .map(item -> item.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            mealDetailsView.setMealDetails(list);
                            Log.i("TAG", list.size() + "");
                        },
                        error -> {
                            mealDetailsView.setErrorMessage(error.getMessage());
                        }
                );
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
}
