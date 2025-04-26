package com.example.mealio.view.mainScreen.star;

import com.example.mealio.model.db.Meal;


public interface StarMealDetailsView {
    void setMealDetails(Meal meal);
    void setErrorMessage (String errorMessage);
}
