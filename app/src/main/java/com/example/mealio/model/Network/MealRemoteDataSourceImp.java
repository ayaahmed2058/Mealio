package com.example.mealio.model.Network;

import com.example.mealio.model.pojo.AreaListResponse;
import com.example.mealio.model.pojo.CategoriesResponse;
import com.example.mealio.model.pojo.IngredientListResponse;
import com.example.mealio.model.pojo.MealResponse;
import com.example.mealio.model.pojo.MealsResponse;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImp implements MealRemoteDataSourceInterface {
    private static final String TAG = "MealClient";
    public static final String URL = "https://www.themealdb.com/api/json/v1/1/";
    MealService mealService;
    private static MealRemoteDataSourceImp mealRemoteDataSourceImp = null;

    public static MealRemoteDataSourceImp getInstance(){
        if(mealRemoteDataSourceImp == null){
            mealRemoteDataSourceImp = new MealRemoteDataSourceImp();
        }
        return mealRemoteDataSourceImp;
    }

    private MealRemoteDataSourceImp(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        mealService = retrofit.create(MealService.class);
    }


    @Override
    public Single<MealsResponse> makeNetworkCallBackForRandomMeal (){

       return mealService.getRandomMeal();

    }

    @Override
    public Single<AreaListResponse> makeNetworkCallBackForAllAreas() {
        return mealService.getAreasList();
    }

    @Override
    public Single<CategoriesResponse> makeNetworkCallBackForAllCategories( ) {
        return mealService.getAllCategories();
    }

    @Override
    public Single<IngredientListResponse> makeNetworkCallBackForAllIngredient() {
        return mealService.getIngredientsList();
    }

    @Override
    public Single<MealResponse> makeNetworkCallBackForMealDetails(String mealID) {
        return mealService.getMealDetails(mealID);
    }

    @Override
    public Single<MealsResponse> filterByCategoryNetworkCallBack(String categoryID) {
        return mealService.getMealsByCategory(categoryID);
    }

    @Override
    public Single<MealsResponse> filterByAreaNetworkCallBack(String areaID) {
        return mealService.getMealsByArea(areaID);
    }

    @Override
    public Single<MealsResponse> filterByIngredientNetworkCallBack(String ingredientID) {
        return mealService.getMealsByIngredient(ingredientID);
    }

    @Override
    public Single<MealsResponse> searchForMeal(String mealName) {
        return mealService.searchMeals(mealName);
    }

    @Override
    public Single<MealsResponse> searchForMealStartsWithA(String letter_A) {
        return mealService.searchMealsByFirstLetter(letter_A);
    }
}
