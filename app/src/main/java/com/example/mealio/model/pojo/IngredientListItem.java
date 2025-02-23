package com.example.mealio.model.pojo;

public class IngredientListItem {
    private String idIngredient;
    private String strIngredient;
    private String strDescription;
    private String strType;

    public IngredientListItem( String strIngredient , String strDescription) {
        this.strDescription = strDescription;
        this.strIngredient = strIngredient;
    }
    public IngredientListItem( ) {

    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public String getStrType() {
        return strType;
    }
}

