package com.example.mealio.model.Network;

import com.example.mealio.model.pojo.AreaListResponse;
import com.example.mealio.model.pojo.CategoriesResponse;
import com.example.mealio.model.pojo.IngredientListResponse;
import com.example.mealio.model.pojo.MealResponse;
import com.example.mealio.model.pojo.MealsResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;

public interface MealRemoteDataSourceInterface {

    Single<MealsResponse> makeNetworkCallBackForRandomMeal ();
    Single<AreaListResponse> makeNetworkCallBackForAllAreas ();
    Single<CategoriesResponse> makeNetworkCallBackForAllCategories();
    Single<IngredientListResponse> makeNetworkCallBackForAllIngredient ();
    Single<MealResponse> makeNetworkCallBackForMealDetails (String mealID);

    Single<MealsResponse> filterByCategoryNetworkCallBack (String categoryID);
    Single<MealsResponse> filterByAreaNetworkCallBack (String areaID);
    Single<MealsResponse> filterByIngredientNetworkCallBack (String ingredientID);
    Single<MealsResponse> searchForMeal (String mealName);
}
