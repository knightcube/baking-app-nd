package com.example.knightcube.bakingapp.ui.detail.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.adapters.IngredientsAdapter;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.ui.detail.activity.RecipeDetailsPresenterInterface;
import com.example.knightcube.bakingapp.ui.detail.activity.RecipeDetailsViewInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment implements RecipeDetailsViewInterface {


    RecyclerView recipeIngredientsRV;
    IngredientsAdapter ingredientsAdapter;
    public IngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_ingredients, container, false);
        recipeIngredientsRV = rootView.findViewById(R.id.recipe_ingredients_rv);

        return rootView;
    }

    @Override
    public void displayRecipesIngredients(Recipe recipe) {
        if (recipe.getIngredients() != null) {
            ingredientsAdapter = new IngredientsAdapter(recipe.getIngredients(), getContext());
            recipeIngredientsRV.setLayoutManager(new LinearLayoutManager(getContext()));
            recipeIngredientsRV.setAdapter(ingredientsAdapter);
        }else{
            showToast("No ingredients to display");
        }
    }

    @Override
    public void displayRecipeSteps() {

    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
