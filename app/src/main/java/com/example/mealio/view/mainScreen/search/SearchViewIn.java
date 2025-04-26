package com.example.mealio.view.mainScreen.search;

import com.example.mealio.model.pojo.MealSummary;

import java.util.List;

public interface SearchViewIn {
    void showLoading();
    void hideLoading();
    void showMeals(List<MealSummary> meals);
    void showError(String message);
}
