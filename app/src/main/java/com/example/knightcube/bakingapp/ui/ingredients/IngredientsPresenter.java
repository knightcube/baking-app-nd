package com.example.knightcube.bakingapp.ui.ingredients;

import com.example.knightcube.bakingapp.models.Recipe;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public class IngredientsPresenter implements IngredientsPresenterInterface {

    private IngredientsViewInterface recipeDetailsViewInterface;
    private Recipe recipe;
    public IngredientsPresenter(IngredientsActivity recipeDetailsActivity, Recipe selectedRecipe) {
        this.recipeDetailsViewInterface =recipeDetailsActivity;
        this.recipe = selectedRecipe;
    }

    @Override
    public void getRecipeIngredients() {
       recipeDetailsViewInterface.displayRecipesIngredients(recipe);
    }
}
