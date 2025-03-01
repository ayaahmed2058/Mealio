package com.example.mealio.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface WeekPlanDAO {
    @Query("SELECT * from mealPlanner_table")
    Observable<List<WeekPlanner>> getStoredPlanningMeal();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlanningMeal(WeekPlanner meal);

    @Delete
    Completable deletePlanningMeal(WeekPlanner meal);

    @Query("DELETE FROM mealPlanner_table")
    Completable clearTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllPlanningMeals(List<WeekPlanner> meals);
}
