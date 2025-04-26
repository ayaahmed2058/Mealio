package com.example.mealio.view.mainScreen.plan;

import com.example.mealio.model.db.WeekPlanner;

public interface OnDeletePlanClickListener {
    void deleteFromPlan(WeekPlanner meal);
}
