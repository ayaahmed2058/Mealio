package com.example.mealio.model.Network;

import android.util.Log;

import com.example.mealio.model.pojo.AreaListResponse;
import com.example.mealio.model.pojo.CategoryListResponse;
import com.example.mealio.model.pojo.MealsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
                .build();
        mealService = retrofit.create(MealService.class);
    }


    @Override
    public void makeNetworkCallBackForRandomMeal (NetworkCallBack networkCallBack){

        Call<MealsResponse> call = mealService.getRandomMeal();
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse:CallBack" + response.raw() + response.body());
                    networkCallBack.onSuccessResult(response.body().getMeals());
                }
            }
            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure:CallBack");
                networkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });

    }

    @Override
    public void makeNetworkCallBackForAllAreas(AreaNetworkCallBack areaNetworkCallBack) {
        Call<AreaListResponse> call = mealService.getAreasList();
        call.enqueue(new Callback<AreaListResponse>() {
            @Override
            public void onResponse(Call<AreaListResponse> call, Response<AreaListResponse> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse:CallBack" + response.raw() + response.body());
                    areaNetworkCallBack.onSuccessResultForArea(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<AreaListResponse> call, Throwable t) {
                Log.i(TAG, "onFailure:CallBack");
                areaNetworkCallBack.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });

    }

    @Override
    public void makeNetworkCallBackForAllCategories(CategoryNetworkCallBack categoryNetworkCallBack) {
        Call<CategoryListResponse> call = mealService.getCategoriesList();
        call.enqueue(new Callback<CategoryListResponse>() {
            @Override
            public void onResponse(Call<CategoryListResponse> call, Response<CategoryListResponse> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse:CallBack" + response.raw() + response.body());
                    categoryNetworkCallBack.onSuccessResult(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<CategoryListResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void makeNetworkCallBackForAllIngredients(NetworkCallBack networkCallBack) {

    }
}
