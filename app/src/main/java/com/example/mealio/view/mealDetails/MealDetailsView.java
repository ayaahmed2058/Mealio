package com.example.mealio.view.mealDetails;

import com.example.mealio.model.db.Meal;
import java.util.List;

public interface MealDetailsView {
    void setMealDetails(List<Meal> meals);
    void setErrorMessage (String errorMessage);
}
