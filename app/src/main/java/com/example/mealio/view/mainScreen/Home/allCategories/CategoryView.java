package com.example.mealio.view.mainScreen.Home.allCategories;

import com.example.mealio.model.pojo.Category;
import java.util.List;

public interface CategoryView {
    void setCategories(List<Category> categories);
    void setErrorMessage (String errorMessage);
}
