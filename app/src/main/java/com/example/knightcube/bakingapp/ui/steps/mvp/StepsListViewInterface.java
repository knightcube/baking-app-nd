package com.example.knightcube.bakingapp.ui.steps.mvp;

import com.example.knightcube.bakingapp.models.Recipe;

/**
 * Created by Rajat Kumar Gupta on 29/06/2018.
 */
public interface StepsListViewInterface {
    void displayRecipeSteps(Recipe recipe);
    void displayError(String e);
    void showToast(String str);
}
