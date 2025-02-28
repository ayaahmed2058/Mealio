package com.example.mealio.presenter;

import android.annotation.SuppressLint;
import android.util.Log;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.pojo.CategoriesResponse;
import com.example.mealio.view.mainScreen.Home.allCategoriesView.CategoryView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllCategoryPresenter {

    private MealRepository mealRepository;
    private CategoryView categoryView;

    public AllCategoryPresenter(CategoryView categoryView, MealRepository mealRepository){
        this.categoryView = categoryView;
        this.mealRepository = mealRepository;
    }

    @SuppressLint("CheckResult")
    public void getAllCategories(){
        Single<CategoriesResponse> categoriesResponseObservable = mealRepository.getAllCategories();
        categoriesResponseObservable.subscribeOn(Schedulers.io())
                .map(item -> item.getCategories())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            categoryView.setCategories(list);
                            Log.i("TAG", list.size() + "");
                        },
                        error -> {
                            categoryView.setErrorMessage(error.getMessage());
                        }
                );
    }


}
