package com.example.mealio.presenter;

import android.annotation.SuppressLint;
import android.util.Log;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.db.WeekPlanner;
import com.example.mealio.view.mainScreen.plan.PlanView;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeekPlanPresenter {
    private MealRepository mealRepository;
    private PlanView planView;

    public WeekPlanPresenter (PlanView planView, MealRepository mealRepository){
        this.mealRepository = mealRepository;
        this.planView = planView;
    }

    @SuppressLint("CheckResult")
    public void getPlannedMeal(){
        Observable<List<WeekPlanner>> storedProduct= mealRepository.getStoredPlanningMeals();
        storedProduct.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            planView.setMeals(list);
                            Log.i("TAG", list.size() + "");
                        },
                        error -> {
                            planView.setErrorMessage(error.getMessage());
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void deleteFromPlan (WeekPlanner meal){
        Completable deleteProduct = mealRepository.deletePlanningMeal(meal);
        deleteProduct.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> Log.i("TAG", "Meal deleted Successfully From Plan"),
                        error -> {
                            planView.setErrorMessage(error.getMessage());
                        }
                );

    }
}
