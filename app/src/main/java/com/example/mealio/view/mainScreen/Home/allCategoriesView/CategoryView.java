package com.example.mealio.view.mainScreen.Home.allCategoriesView;

import com.example.mealio.model.pojo.AreaListItem;
import com.example.mealio.model.pojo.Category;

import java.util.List;

public interface CategoryView {
    void setCategories(List<Category> categories);
    void setErrorMessage (String errorMessage);
}
