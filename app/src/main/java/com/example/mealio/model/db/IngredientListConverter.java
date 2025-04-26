package com.example.mealio.model.db;

import androidx.room.TypeConverter;

import com.example.mealio.model.pojo.IngredientListItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class IngredientListConverter {

    @TypeConverter
    public static String fromIngredientList(List<IngredientListItem> ingredientList) {
        if (ingredientList == null) return null;
        Gson gson = new Gson();
        return gson.toJson(ingredientList);
    }

    @TypeConverter
    public static List<IngredientListItem> toIngredientList(String ingredientListString) {
        if (ingredientListString == null) return null;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<IngredientListItem>>() {}.getType();
        return gson.fromJson(ingredientListString, listType);
    }
}
