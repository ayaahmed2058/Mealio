package com.example.mealio.presenter;

import android.annotation.SuppressLint;
import android.util.Log;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.db.Meal;
import com.example.mealio.view.mainScreen.star.StarMealView;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StarPresenter {

    private MealRepository mealRepository;
    private StarMealView starMealView;

    public StarPresenter (StarMealView starMealView, MealRepository mealRepository){
        this.mealRepository = mealRepository;
        this.starMealView = starMealView;
    }

    @SuppressLint("CheckResult")
    public void getStoredMeal(){
        Observable<List<Meal>> storedProduct= mealRepository.getStoredMeal();
        storedProduct.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            starMealView.setMeals(list);
                            Log.i("TAG", list.size() + "");
                        },
                        error -> {
                            starMealView.setErrorMessage(error.getMessage());
                        }
                );
    }

    @SuppressLint("CheckResult")
    public void deleteFromFav (Meal meal){
        Completable deleteProduct = mealRepository.deleteMeal(meal);
        deleteProduct.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> Log.i("TAG", "Meal deleted Successfully"),
                        error -> {
                            starMealView.setErrorMessage(error.getMessage());
                        }
                );

    }

}
