package com.example.knightcube.bakingapp.ui.ingredients;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.adapters.IngredientsAdapter;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.ui.steps.mvp.StepListActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IngredientsActivity extends AppCompatActivity implements IngredientsViewInterface {

    @BindView(R.id.recipe_ingredients_rv)
    RecyclerView recipeIngredientsRV;

    @BindView(R.id.recipe_favourite_btn)
    FloatingActionButton recipeFavouriteBtn;

    @BindView(R.id.ingredients_done_btn)
    Button ingredientsDoneBtn;

    @BindView(R.id.selected_recipe)
    TextView selectedRecipe;

    IngredientsAdapter ingredientsAdapter;
    IngredientsPresenter recipeDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        selectedRecipe.setText(getSelectedRecipe().getName());
        setupMVP();
        getIngredients();
    }

    @OnClick(R.id.recipe_favourite_btn)
    public void addToFavourites() {
        SharedPreferences.Editor editor = getSharedPreferences("FAVOURITES", MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(getSelectedRecipe());
        editor.putString("favourite_recipe", json);
        editor.commit();
        showToast("You can now add widget in your Home Screen");
    }

    @OnClick(R.id.ingredients_done_btn)
    public void goToSteps(){
        Intent intent = new Intent(IngredientsActivity.this, StepListActivity.class);
        intent.putExtra("selected_recipe",getSelectedRecipe());
        startActivity(intent);
    }

    private void getIngredients() {
        recipeDetailsPresenter.getRecipeIngredients();
    }

    public Recipe getSelectedRecipe() {
        Bundle data = getIntent().getExtras();
        Recipe recipe = null;
        if (data != null) {
            recipe = (Recipe) data.getParcelable("recipe");
        }
        return recipe;
    }

    private void setupMVP() {
        recipeDetailsPresenter = new IngredientsPresenter(IngredientsActivity.this,getSelectedRecipe());
    }

    @Override
    public void displayRecipesIngredients(Recipe recipe) {
        if (recipe.getIngredients() != null) {
            ingredientsAdapter = new IngredientsAdapter(recipe.getIngredients(), this);
            recipeIngredientsRV.setLayoutManager(new LinearLayoutManager(this));
            recipeIngredientsRV.setAdapter(ingredientsAdapter);
        } else {
            showToast("No ingredients to display");
        }
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displaySelectedRecipe() {

    }

}
