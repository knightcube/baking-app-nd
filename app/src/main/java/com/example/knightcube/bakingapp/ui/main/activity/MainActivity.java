package com.example.knightcube.bakingapp.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.adapters.RecipeAdapter;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.ui.detail.activity.RecipeDetailsActivity;
import com.example.knightcube.bakingapp.ui.detail.fragment.IngredientsFragment;
import com.example.knightcube.bakingapp.ui.main.fragment.RecipesFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public class MainActivity extends AppCompatActivity implements MainViewInterface,RecipeAdapter.ListItemClickListener{

    @Nullable
    @BindView(R.id.recipe_recycler_view)
    RecyclerView recyclerView;

    RecipeAdapter recipeAdapter;
    private static boolean mTwoPane;
    private static final String TAG = "MainActivity" ;
    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(MainActivity.this);
        if(findViewById(R.id.tab_recipe_container)!=null){
            mTwoPane = true;
        }else{
            mTwoPane = false;
        }

        setupMVP();
        getRecipeList();
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews() {
    }

    private void getRecipeList() {
        mainPresenter.getRecipes();
    }
    private static boolean isTwoPane(){
        return mTwoPane;
    }

    @Override
    public void displayRecipes(List<Recipe> recipe) {
        if(recipe!=null){
            Log.i(TAG, "displayRecipes: "+recipe.get(1).getName());
            recipeAdapter = new RecipeAdapter(recipe, MainActivity.this, MainActivity.this);
            if(!mTwoPane) {

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
            }else{
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                RecipesFragment recipesFragment = new RecipesFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.tab_recipe_container,recipesFragment)
                        .commit();
                IngredientsFragment ingredientsFragment = new IngredientsFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.tab_ingredients_container,ingredientsFragment)
                        .commit();
            }
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
        Intent intent = new Intent(MainActivity.this,RecipeDetailsActivity.class);
        intent.putExtra("recipe",recipeList.get(clickedItemIndex));
        startActivity(intent);
    }
    //
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.sa
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
