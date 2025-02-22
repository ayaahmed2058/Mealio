package com.example.mealio.model.Network;

import com.example.mealio.model.pojo.IngredientListItem;

import java.util.List;

public interface IngredientNetworkCallBack {
    void onSuccessResultForIngredient(List<IngredientListItem> ingredientListItems);
    void onFailureResult (String errorMessage);
}
