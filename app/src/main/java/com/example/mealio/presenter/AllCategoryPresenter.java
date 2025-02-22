package com.example.mealio.presenter;

import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.CategoryNetworkCallBack;
import com.example.mealio.model.pojo.Category;
import com.example.mealio.view.mainScreen.Home.allCategoriesView.CategoryView;

import java.util.List;

public class AllCategoryPresenter implements CategoryNetworkCallBack {
    private MealRepository mealRepository;
    private CategoryView categoryView;

    public AllCategoryPresenter(CategoryView categoryView, MealRepository mealRepository){
        this.categoryView = categoryView;
        this.mealRepository = mealRepository;
    }

    public void getAllCategories(){
        mealRepository.getAllCategories(this);
    }


    @Override
    public void onSuccessResultForCategories(List<Category> categoryListResponses) {
        categoryView.setCategories(categoryListResponses);
    }

    @Override
    public void onFailureResult(String errorMessage) {
        categoryView.setErrorMessage(errorMessage);
    }
}
