package com.example.mealio.view.mainScreen;


import com.example.mealio.model.pojo.MealSummary;

import java.util.List;

public interface AllMealView {
    void setMeals(List<MealSummary> meals);
    void setErrorMessage (String errorMessage);
}
