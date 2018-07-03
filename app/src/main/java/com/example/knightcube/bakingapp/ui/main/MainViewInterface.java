package com.example.knightcube.bakingapp.ui.main;

import com.example.knightcube.bakingapp.models.Recipe;

import java.util.List;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
interface MainViewInterface {
    void displayRecipes(List<Recipe> recipes);
    void displayError(String e);
}
