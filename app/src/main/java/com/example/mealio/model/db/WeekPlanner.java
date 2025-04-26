package com.example.mealio.model.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mealPlanner_table")
public class WeekPlanner {

    @PrimaryKey
    @NonNull
    private String idMeal;

    private String strMeal;

    private String date;

    private String mealType;

    private String mealThump;

    public WeekPlanner(@NonNull String idMeal, String strMeal, String date, String mealType, String mealThump) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.date = date;
        this.mealType = mealType;
        this.mealThump = mealThump;
    }

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealThump() {
        return mealThump;
    }

    public void setMealThump(String mealThump) {
        this.mealThump = mealThump;
    }
}
