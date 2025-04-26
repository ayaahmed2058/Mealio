package com.example.mealio.view.mainScreen.Home.getMealByCategory;

import com.example.mealio.model.pojo.MealSummary;

import java.util.List;

public interface MealByCategoryView {
    void setMealsByCategory(List<MealSummary> meals);
    void setErrorMessage (String errorMessage);
}
