package com.example.mealio.presenter;

import android.annotation.SuppressLint;

import com.example.mealio.model.MealRepository;
import com.example.mealio.view.mainScreen.search.SearchViewIn;
import java.util.Collections;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SearchPresenter {
    private SearchViewIn searchView;
    private MealRepository mealRepository;

    public SearchPresenter(SearchViewIn searchView, MealRepository mealRepository) {
        this.searchView = searchView;
        this.mealRepository = mealRepository;
    }

    @SuppressLint("CheckResult")
    public void searchMeal(String mealName) {
        if (mealName.trim().isEmpty()) {
            searchView.showMeals(Collections.emptyList());
            return;
        }

        searchView.showLoading();

        mealRepository.searchMeals(mealName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    searchView.hideLoading();
                    if (response.getMeals() != null && !response.getMeals().isEmpty()) {
                        searchView.showMeals(response.getMeals());
                    } else {
                        searchView.showError("No meals found.");
                    }
                }, error -> {
                    searchView.hideLoading();
                    searchView.showError(error.getMessage());
                });
    }
}



