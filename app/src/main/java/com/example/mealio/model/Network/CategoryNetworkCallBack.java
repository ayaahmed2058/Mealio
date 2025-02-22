package com.example.mealio.model.Network;


import com.example.mealio.model.pojo.CategoryListItem;


import java.util.List;

public interface CategoryNetworkCallBack {
    void onSuccessResult (List<CategoryListItem> categoryListResponses);
    void onFailureResult (String errorMessage);

}
