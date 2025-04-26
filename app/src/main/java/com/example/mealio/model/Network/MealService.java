package com.example.mealio.model.Network;

import com.example.mealio.model.pojo.AreaListResponse;
import com.example.mealio.model.pojo.CategoriesResponse;
import com.example.mealio.model.pojo.CategoryListResponse;
import com.example.mealio.model.pojo.IngredientListResponse;
import com.example.mealio.model.pojo.MealResponse;
import com.example.mealio.model.pojo.MealsResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {


        @GET("random.php")
        Single<MealsResponse> getRandomMeal();

        @GET("filter.php")
        Single<MealsResponse> getMealsByCategory(@Query("c") String category);

        @GET("filter.php")
        Single<MealsResponse> getMealsByArea(@Query("a") String area);

        @GET("filter.php")
        Single<MealsResponse> getMealsByIngredient(@Query("i") String ingredient);

        @GET("categories.php")
        Single<CategoriesResponse> getAllCategories();

        @GET("list.php?c=list")
        Single<CategoryListResponse> getCategoriesList();

        @GET("list.php?a=list")
        Single<AreaListResponse> getAreasList();

        @GET("list.php?i=list")
        Single<IngredientListResponse> getIngredientsList();

        @GET("search.php")
        Single<MealsResponse> searchMeals(@Query("s") String mealName);

        @GET("lookup.php")
        Single<MealResponse> getMealDetails(@Query("i") String mealId);

        @GET("search.php")
        Single<MealsResponse> searchMealsByFirstLetter(@Query("f") String letter_A);
}

