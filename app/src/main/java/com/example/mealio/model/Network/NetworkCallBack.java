package com.example.mealio.model.Network;


import com.example.mealio.model.pojo.MealSummary;

import java.util.List;

public interface NetworkCallBack {

    void onSuccessResult (List<MealSummary> mealSummaries);
    void onFailureResult (String errorMessage);
}
