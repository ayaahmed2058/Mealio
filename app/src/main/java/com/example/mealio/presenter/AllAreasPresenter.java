package com.example.mealio.presenter;

import android.annotation.SuppressLint;
import android.util.Log;
import com.example.mealio.model.MealRepository;
import com.example.mealio.model.pojo.AreaListResponse;
import com.example.mealio.view.mainScreen.allAreas.AreaView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllAreasPresenter  {

    private MealRepository mealRepository;
    private AreaView areaView;

    public AllAreasPresenter(AreaView areaView, MealRepository mealRepository){
        this.areaView = areaView;
        this.mealRepository = mealRepository;
    }


    @SuppressLint("CheckResult")
    public void getAllAreas (){
        Single<AreaListResponse> allAreas = mealRepository.getALLAreas();
        allAreas.subscribeOn(Schedulers.io())
                .map(item -> item.getMeals())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            areaView.setAreas(list);
                            Log.i("TAG", list.size() + "");
                        },
                        error -> {
                            areaView.setErrorMessage(error.getMessage());
                        }
                );
    }

}

