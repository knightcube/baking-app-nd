package com.example.knightcube.bakingapp.ui.main.activity;

import com.example.knightcube.bakingapp.models.Recipe;

import java.util.List;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public interface MainViewInterface {
    void displayRecipes(List<Recipe> recipes);
    void displayError(String e);
    void showToast(String str);
}
