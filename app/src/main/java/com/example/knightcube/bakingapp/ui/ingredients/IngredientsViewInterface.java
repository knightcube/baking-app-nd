package com.example.knightcube.bakingapp.ui.ingredients;

import com.example.knightcube.bakingapp.models.Recipe;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public interface IngredientsViewInterface {
    void displayRecipesIngredients(Recipe recipe);
    void displayRecipeSteps();
    void displayError(String e);
    void showToast(String str);
}
