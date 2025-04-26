package com.example.mealio.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Meal.class, WeekPlanner.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    private static AppDB instance = null;
    public abstract MealDAO getMealDAO();
    public abstract WeekPlanDAO getWeekPlanDAO();

    public static synchronized AppDB getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDB.class, "meal_db").build();
        }
        return instance;
    }

}
