package com.example.knightcube.bakingapp.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.adapters.RecipeAdapter;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.ui.ingredients.IngredientsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface,RecipeAdapter.ListItemClickListener{

    @BindView(R.id.recipe_recycler_view)
    RecyclerView recyclerView;

    RecipeAdapter recipeAdapter;
    private int state;//0 for linear, and 1 for grid

    private static final String TAG = "MainActivity" ;
    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(MainActivity.this);
        if(isTablet(this)){
            state = 1;
        }else{
            state = 0;
        }
        setupMVP();
        getRecipeList();
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews() {
        if(state==0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        } else{
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        }
    }

    private void getRecipeList() {
        mainPresenter.getRecipes();
    }

    @Override
    public void displayRecipes(List<Recipe> recipe) {
        if(recipe!=null){
            Log.i(TAG, "displayRecipes: "+recipe.get(1).getName());
            recipeAdapter = new RecipeAdapter(recipe,MainActivity.this, MainActivity.this);
            setupViews();
            recyclerView.setAdapter(recipeAdapter);
        }else{
            Log.i(TAG, "displayRecipes: Response is null");
        }
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onListItemClick(int clickedItemIndex, List<Recipe> recipeList) {
        Intent intent = new Intent(MainActivity.this,IngredientsActivity.class);
        intent.putExtra("recipe",recipeList.get(clickedItemIndex));
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_github) {
            return true;
        }
        else if(id==R.id.action_change_view){
            if(state==0){
                state=1;
            }else{
                state=0;
            }
            setupViews();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged: ");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(TAG, "onConfigurationChanged: Landscape");
           state = 1;
           setupViews();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.i(TAG, "onConfigurationChanged: Portrait");
            state = 0;
            setupViews();
        }
    }
    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
}
