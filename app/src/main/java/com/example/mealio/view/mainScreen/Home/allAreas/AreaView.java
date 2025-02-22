package com.example.mealio.view.mainScreen.Home.allAreas;

import com.example.mealio.model.pojo.AreaListItem;

import java.util.List;

public interface AreaView {
    void setAreas(List<AreaListItem> areas);
    void setErrorMessage (String errorMessage);
}
