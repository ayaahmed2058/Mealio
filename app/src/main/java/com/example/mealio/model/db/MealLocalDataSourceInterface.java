package com.example.mealio.model.db;

import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface MealLocalDataSourceInterface {
    Completable addMeal(Meal meal);

    Observable<List<Meal>> getStarsMeal();

    Completable deleteMeal(Meal meal);

    Observable<Meal> getMealById (String mealId);

    Completable addPlanningMeal(WeekPlanner meal);

    Observable<List<WeekPlanner>> getPlanningMeals();

    Completable deletePlaningMeal(WeekPlanner meal);

}
