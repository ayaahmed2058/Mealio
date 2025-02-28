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
    Observable<AreaListResponse> makeNetworkCallBackForAllAreas ();
    Observable<CategoriesResponse> makeNetworkCallBackForAllCategories();
    Observable<IngredientListResponse> makeNetworkCallBackForAllIngredient ();
    Observable<MealResponse> makeNetworkCallBackForMealDetails (String mealID);

    Observable<MealsResponse> filterByCategoryNetworkCallBack (String categoryID);
    Observable<MealsResponse> filterByAreaNetworkCallBack (String areaID);
    Observable<MealsResponse> filterByIngredientNetworkCallBack (String ingredientID);
    Observable<MealsResponse> searchForMeal (String mealName);
}
