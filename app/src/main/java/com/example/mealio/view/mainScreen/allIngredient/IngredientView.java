package com.example.mealio.view.mainScreen.allIngredient;

import com.example.mealio.model.pojo.Category;
import com.example.mealio.model.pojo.IngredientListItem;

import java.util.List;

public interface IngredientView {
    void setIngredient(List<IngredientListItem> ingredientListItems);
    void setErrorMessage (String errorMessage);
}
