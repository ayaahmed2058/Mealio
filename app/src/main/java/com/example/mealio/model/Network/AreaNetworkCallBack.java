package com.example.mealio.model.Network;

import com.example.mealio.model.pojo.AreaListItem;

import java.util.List;

public interface AreaNetworkCallBack {
    void onSuccessResultForArea(List<AreaListItem> mealSummaries);
    void onFailureResult (String errorMessage);
}
