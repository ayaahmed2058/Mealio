package com.example.mealio.model.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealDAO {

    @Query("SELECT * from favMeal_table")
    Observable<List<Meal>> getStoredMeal();

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    Completable insertMeal(Meal meal);

    @Delete
    Completable deleteMeal(Meal meal);


}
