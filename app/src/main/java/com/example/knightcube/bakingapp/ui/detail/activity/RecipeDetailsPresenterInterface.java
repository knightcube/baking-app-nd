package com.example.knightcube.bakingapp.ui.detail.activity;

import com.example.knightcube.bakingapp.models.Ingredient;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.models.Step;

import java.util.List;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public interface RecipeDetailsPresenterInterface {
    void getRecipeIngredients();
    void getRecipeSteps();
}
