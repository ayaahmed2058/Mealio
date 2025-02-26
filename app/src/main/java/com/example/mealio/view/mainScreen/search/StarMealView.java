package com.example.mealio.view.mainScreen.search;

import com.example.mealio.model.db.Meal;

import java.util.List;

public interface StarMealView {
    void setMeals(List<Meal> meals);
    void setErrorMessage (String errorMessage);
}
