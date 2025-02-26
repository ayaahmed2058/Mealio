package com.example.mealio.presenter;

import com.example.mealio.model.MealRepository;
import com.example.mealio.model.Network.AreaNetworkCallBack;
import com.example.mealio.model.pojo.AreaListItem;
import com.example.mealio.model.pojo.MealsResponse;
import com.example.mealio.view.mainScreen.Home.allAreas.AreaView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllAreasPresenter implements AreaNetworkCallBack {

    private MealRepository mealRepository;
    private AreaView areaView;

    public AllAreasPresenter(AreaView areaView, MealRepository mealRepository){
        this.areaView = areaView;
        this.mealRepository = mealRepository;
    }


    public void getAllAreas (){
        mealRepository.getALLAreas(this);
    }


    @Override
    public void onSuccessResultForArea(List<AreaListItem> areaListItems) {
        areaView.setAreas(areaListItems);
    }

    @Override
    public void onFailureResult(String errorMessage) {
        areaView.setErrorMessage(errorMessage);
    }

}

