package com.example.knightcube.bakingapp.ui.detail;

import com.example.knightcube.bakingapp.models.Ingredient;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.models.Step;

import java.util.List;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public class RecipeDetailsPresenter implements RecipeDetailsPresenterInterface{

    private RecipeDetailsViewInterface recipeDetailsViewInterface;
    private Recipe recipe;
    public RecipeDetailsPresenter(RecipeDetailsActivity recipeDetailsActivity, Recipe selectedRecipe) {
        this.recipeDetailsViewInterface =recipeDetailsActivity;
        this.recipe = selectedRecipe;
    }

    @Override
    public void getRecipeIngredients() {
       recipeDetailsViewInterface.displayRecipesIngredients(recipe);
    }

    @Override
    public void getRecipeSteps() {

    }


}
