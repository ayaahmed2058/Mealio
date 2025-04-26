package com.example.mealio.presenter;

import android.annotation.SuppressLint;
import android.util.Log;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.pojo.IngredientListResponse;
import com.example.mealio.view.mainScreen.allIngredient.IngredientView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllIngredientPresenter  {
    private MealRepository mealRepository;
    private IngredientView ingredientView;

    public AllIngredientPresenter(IngredientView ingredientView, MealRepository mealRepository){
        this.ingredientView = ingredientView;
        this.mealRepository = mealRepository;
    }

    @SuppressLint("CheckResult")
    public void getAllIngredient(){
        Single<IngredientListResponse> ingredientListResponseObservable = mealRepository.getAllIngredient();
        ingredientListResponseObservable.subscribeOn(Schedulers.io())
                .map(item -> item.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            ingredientView.setIngredient(list);
                            Log.i("TAG", list.size() + "");
                        },
                        error -> {
                            ingredientView.setErrorMessage(error.getMessage());
                        }
                );
    }

}
