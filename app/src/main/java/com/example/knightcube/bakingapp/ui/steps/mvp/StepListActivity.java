package com.example.knightcube.bakingapp.ui.steps.mvp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.knightcube.bakingapp.R;

import com.example.knightcube.bakingapp.adapters.IngredientsAdapter;
import com.example.knightcube.bakingapp.adapters.StepsAdapter;
import com.example.knightcube.bakingapp.models.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepListActivity extends AppCompatActivity implements StepsListViewInterface {

    @BindView(R.id.step_list_rv)
    RecyclerView recipeStepsRV;

    StepsListPresenter stepsListPresenter;
    StepsAdapter stepsAdapter;

    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getTitle());
        ButterKnife.bind(this);
        if (findViewById(R.id.step_detail_container) != null) {
            mTwoPane = true;
        }

        setupMVP();
        setupRecyclerView();
        getSteps();
    }

    private void setupMVP() {
        stepsListPresenter = new StepsListPresenter(StepListActivity.this, getSelectedRecipe());
    }

    private void setupRecyclerView() {
        StepsAdapter stepsAdapter = new StepsAdapter(getSelectedRecipe().getSteps(), StepListActivity.this, mTwoPane);
        recipeStepsRV.setAdapter(stepsAdapter);
    }

    @Override
    public void displayRecipeSteps(Recipe recipe) {
        if (recipe.getIngredients() != null) {
            stepsAdapter = new StepsAdapter(recipe.getSteps(), this, mTwoPane);
            recipeStepsRV.setLayoutManager(new LinearLayoutManager(this));
            recipeStepsRV.setAdapter(stepsAdapter);
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

    public void getSteps() {
        stepsListPresenter.getRecipeSteps();
    }

    public Recipe getSelectedRecipe() {
        Bundle data = getIntent().getExtras();
        Recipe recipe = (Recipe) data.getParcelable("selected_recipe");
        return recipe;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
