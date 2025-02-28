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
        Observable<MealsResponse> getMealsByCategory(@Query("c") String category);

        @GET("filter.php")
        Observable<MealsResponse> getMealsByArea(@Query("a") String area);

        @GET("filter.php")
        Observable<MealsResponse> getMealsByIngredient(@Query("i") String ingredient);

        @GET("categories.php")
        Observable<CategoriesResponse> getAllCategories();

        @GET("list.php?c=list")
        Call<CategoryListResponse> getCategoriesList();

        @GET("list.php?a=list")
        Observable<AreaListResponse> getAreasList();

        @GET("list.php?i=list")
        Observable<IngredientListResponse> getIngredientsList();

        @GET("search.php")
        Observable<MealsResponse> searchMeals(@Query("s") String mealName);

        @GET("lookup.php")
        Observable<MealResponse> getMealDetails(@Query("i") String mealId);
    }

