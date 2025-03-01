package com.example.mealio.model;


import com.example.mealio.model.Network.MealRemoteDataSourceInterface;
import com.example.mealio.model.db.BackupMeals;
import com.example.mealio.model.db.Meal;
import com.example.mealio.model.db.MealLocalDataSourceInterface;
import com.example.mealio.model.db.WeekPlanner;
import com.example.mealio.model.pojo.AreaListResponse;
import com.example.mealio.model.pojo.CategoriesResponse;
import com.example.mealio.model.pojo.IngredientListResponse;
import com.example.mealio.model.pojo.MealResponse;
import com.example.mealio.model.pojo.MealsResponse;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;


public class MealRepository {
    private MealRemoteDataSourceInterface mealRemoteDataSourceInterface;
    private MealLocalDataSourceInterface mealLocalDataSourceInterface;
    private static MealRepository instance = null;

    private MealRepository(MealLocalDataSourceInterface mealLocalDataSourceInterface, MealRemoteDataSourceInterface mealRemoteDataSourceInterface) {
        this.mealLocalDataSourceInterface = mealLocalDataSourceInterface;
        this.mealRemoteDataSourceInterface = mealRemoteDataSourceInterface;
    }

    public static MealRepository getInstance (MealLocalDataSourceInterface mealLocalDataSourceInterface, MealRemoteDataSourceInterface mealRemoteDataSourceInterface){
        if(instance == null){
            instance = new MealRepository(mealLocalDataSourceInterface, mealRemoteDataSourceInterface);
        }
        return  instance;
    }


    public Single<MealsResponse> getRandomMeal (){
       return mealRemoteDataSourceInterface.makeNetworkCallBackForRandomMeal();
    }

    public Single<AreaListResponse> getALLAreas (){
        return mealRemoteDataSourceInterface.makeNetworkCallBackForAllAreas();
    }

    public Single<CategoriesResponse> getAllCategories (){
        return mealRemoteDataSourceInterface.makeNetworkCallBackForAllCategories();
    }

    public Single<IngredientListResponse> getAllIngredient(){
        return mealRemoteDataSourceInterface.makeNetworkCallBackForAllIngredient();
    }

    public Single<MealResponse> getMealDetails (String mealID){
        return mealRemoteDataSourceInterface.makeNetworkCallBackForMealDetails( mealID);
    }

    public Single<MealsResponse> filterByArea(String areaID){
        return mealRemoteDataSourceInterface.filterByAreaNetworkCallBack(areaID);
    }
    public Single<MealsResponse> filterByCategory(String categoryID){
        return mealRemoteDataSourceInterface.filterByCategoryNetworkCallBack(categoryID);
    }
    public Single<MealsResponse> filterByIngredient(String ingredientID){
        return mealRemoteDataSourceInterface.filterByIngredientNetworkCallBack(ingredientID);
    }

    public Single<MealsResponse> searchMeals(String mealName) {
        return mealRemoteDataSourceInterface.searchForMeal(mealName);
    }


    public Observable<List<Meal>> getStoredMeal (){
        return mealLocalDataSourceInterface.getStarsMeal();
    }

    public Completable insertMeal (Meal meal){
        return mealLocalDataSourceInterface.addMeal(meal);
    }

    public Completable deleteMeal (Meal meal){
        return mealLocalDataSourceInterface.deleteMeal(meal);
    }

    public Observable<Meal> getMealDetailsById (String mealId){
        return mealLocalDataSourceInterface.getMealById(mealId);
    }

    public Observable<List<WeekPlanner>> getStoredPlanningMeals (){
        return mealLocalDataSourceInterface.getPlanningMeals();
    }

    public Completable insertPlanningMeal (WeekPlanner meal){
        return mealLocalDataSourceInterface.addPlanningMeal(meal);
    }

    public Completable deletePlanningMeal (WeekPlanner meal){
        return mealLocalDataSourceInterface.deletePlaningMeal(meal);
    }

    public Single<MealsResponse> getMealsStartsWith_A(String letter){
        return mealRemoteDataSourceInterface.searchForMealStartsWithA(letter);
    }

    public Completable insertAllMeal(List<Meal> meals){
        return mealLocalDataSourceInterface.insertAllMeals(meals);
    }
    public Completable insertAllPlanningMeal(List<WeekPlanner> meals){
        return mealLocalDataSourceInterface.insertAllPlanningMeals(meals);
    }

    public Completable clearAllStarMeals(){
        return mealLocalDataSourceInterface.clearAllStarMeal();
    }

    public Completable clearAllPlanningMeals(){
        return mealLocalDataSourceInterface.clearAllPlanningMeal();
    }

    public Completable addAllStarMealToFireStore (String userId, List<Meal> meals){
       return mealLocalDataSourceInterface.addAllStarMealToFireStore(userId,meals);
    }



}
