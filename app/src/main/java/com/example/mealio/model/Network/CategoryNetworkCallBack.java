package com.example.mealio.model.Network;


import com.example.mealio.model.pojo.Category;

import java.util.List;

public interface CategoryNetworkCallBack {
    void onSuccessResultForCategories(List<Category> categoryListResponses);
    void onFailureResult (String errorMessage);

}
