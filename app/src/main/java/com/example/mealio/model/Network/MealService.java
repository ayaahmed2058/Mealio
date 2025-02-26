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
        Call<MealsResponse> getRandomMeal();

        @GET("filter.php")
        Observable<MealsResponse> getMealsByCategory(@Query("c") String category);

        @GET("filter.php")
        Observable<MealsResponse> getMealsByArea(@Query("a") String area);

        @GET("filter.php")
        Observable<MealsResponse> getMealsByIngredient(@Query("i") String ingredient);

        @GET("categories.php")
        Call<CategoriesResponse> getAllCategories();

        @GET("list.php?c=list")
        Call<CategoryListResponse> getCategoriesList();

        @GET("list.php?a=list")
        Call<AreaListResponse> getAreasList();

        @GET("list.php?i=list")
        Call<IngredientListResponse> getIngredientsList();

        @GET("search.php")
        Observable<MealsResponse> searchMeals(@Query("s") String mealName);

        @GET("lookup.php")
        Call<MealResponse> getMealDetails(@Query("i") String mealId);
    }

