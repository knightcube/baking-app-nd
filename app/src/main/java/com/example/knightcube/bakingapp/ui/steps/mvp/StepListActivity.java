package com.example.knightcube.bakingapp.ui.steps.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.knightcube.bakingapp.R;

import com.example.knightcube.bakingapp.adapters.StepsAdapter;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.models.Step;
import com.example.knightcube.bakingapp.ui.steps.StepDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepListActivity extends AppCompatActivity implements StepsListViewInterface {

    @BindView(R.id.step_list_rv)
    RecyclerView recipeStepsRV;

    private StepsListPresenter stepsListPresenter;

    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getTitle());
        ButterKnife.bind(this);
        if (findViewById(R.id.step_detail_container) != null) {
            mTwoPane = true;
            //Show default fragment in tablet
            Bundle arguments = new Bundle();
            Step step = getSelectedRecipe().getSteps().get(0);
            arguments.putParcelable(StepDetailFragment.ARG_ITEM_ID, step);
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, fragment)
                    .commit();
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
            StepsAdapter stepsAdapter = new StepsAdapter(recipe.getSteps(), this, mTwoPane);
            recipeStepsRV.setLayoutManager(new LinearLayoutManager(this));
            recipeStepsRV.setAdapter(stepsAdapter);
        } else {
            showToast("No ingredients to display");
        }
    }

    public void displayError(String e) {
        showToast(e);
    }

    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void getSteps() {
        stepsListPresenter.getRecipeSteps();
    }

    private Recipe getSelectedRecipe() {
        Bundle data = getIntent().getExtras();
        return (Recipe) data.getParcelable("selected_recipe");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
