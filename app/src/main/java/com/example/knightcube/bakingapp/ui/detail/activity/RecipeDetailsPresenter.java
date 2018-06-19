package com.example.knightcube.bakingapp.ui.detail.activity;

import com.example.knightcube.bakingapp.models.Recipe;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public class RecipeDetailsPresenter implements RecipeDetailsPresenterInterface {

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
