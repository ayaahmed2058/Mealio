package com.example.mealio.view.mainScreen.plan;

import com.example.mealio.model.db.WeekPlanner;
import java.util.List;

public interface PlanView {
    void setMeals(List<WeekPlanner> meals);
    void setErrorMessage (String errorMessage);
}
