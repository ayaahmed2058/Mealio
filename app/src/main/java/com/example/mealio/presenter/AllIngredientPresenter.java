package com.example.mealio.presenter;

import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.IngredientNetworkCallBack;
import com.example.mealio.model.pojo.IngredientListItem;
import com.example.mealio.view.mainScreen.Home.allIngredient.IngredientView;

import java.util.List;

public class AllIngredientPresenter implements IngredientNetworkCallBack {
    private MealRepository mealRepository;
    private IngredientView ingredientView;

    public AllIngredientPresenter(IngredientView ingredientView, MealRepository mealRepository){
        this.ingredientView = ingredientView;
        this.mealRepository = mealRepository;
    }

    public void getAllIngredient(){
        mealRepository.getAllIngredient(this);
    }


    @Override
    public void onSuccessResultForIngredient(List<IngredientListItem> ingredientListItems) {
        ingredientView.setIngredient(ingredientListItems);
    }

    @Override
    public void onFailureResult(String errorMessage) {
        ingredientView.setErrorMessage(errorMessage);
    }
}
