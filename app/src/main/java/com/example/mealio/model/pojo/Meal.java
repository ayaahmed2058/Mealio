package com.example.mealio.model.pojo;


import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private String idMeal, strMeal, strCategory, strArea, strInstructions, strMealThumb, strYoutube;
    private String strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
            strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
            strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
            strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20;

    private String strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5,
            strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
            strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15,
            strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20;

    private List<IngredientListItem> ingredientList = new ArrayList<>();

    public String getIdMeal() { return idMeal; }
    public String getStrMeal() { return strMeal; }
    public String getStrCategory() { return strCategory; }
    public String getStrArea() { return strArea; }
    public String getStrInstructions() { return strInstructions; }
    public String getStrMealThumb() { return strMealThumb; }
    public String getStrYoutube() { return strYoutube; }
    public List<IngredientListItem> getIngredientList() { return ingredientList; }

    public void extractIngredients() {
        ingredientList.clear();
        for (int i = 1; i <= 20; i++) {
            try {
                String ingredient = (String) Meal.class.getDeclaredField("strIngredient" + i).get(this);
                String measure = (String) Meal.class.getDeclaredField("strMeasure" + i).get(this);

                if (ingredient != null && !ingredient.trim().isEmpty()) {
                    ingredientList.add(new IngredientListItem(ingredient, measure));
                }
            } catch (Exception ignored) {}
        }
    }
}
