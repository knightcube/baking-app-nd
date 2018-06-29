package com.example.knightcube.bakingapp.ui.steps.mvp;

import com.example.knightcube.bakingapp.models.Recipe;

/**
 * Created by Rajat Kumar Gupta on 29/06/2018.
 */
public class StepsListPresenter implements StepsListPresenterInterface {

    private StepsListViewInterface stepsListViewInterface;
    private Recipe selectedRecipe;
    public StepsListPresenter(StepsListViewInterface stepsListViewInterface, Recipe selectedRecipe){
        this.stepsListViewInterface = stepsListViewInterface;
        this.selectedRecipe = selectedRecipe;
    }

    @Override
    public void getRecipeSteps() {
        stepsListViewInterface.displayRecipeSteps(selectedRecipe);
    }
}

