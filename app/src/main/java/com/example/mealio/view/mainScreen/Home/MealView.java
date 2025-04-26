package com.example.mealio.view.mainScreen.Home;


import com.example.mealio.model.pojo.MealSummary;

import java.util.List;

public interface MealView {
    void setMeals(List<MealSummary> meals);
    void setErrorMessage (String errorMessage);
}
