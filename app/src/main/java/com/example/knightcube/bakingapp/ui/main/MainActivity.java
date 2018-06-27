package com.example.knightcube.bakingapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.adapters.RecipeAdapter;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.ui.detail.RecipeDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface,RecipeAdapter.ListItemClickListener{

    @BindView(R.id.recipe_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.recipe_app_banner_image)
    ImageView recipeAppBannerImage;

    RecipeAdapter recipeAdapter;

    private static final String TAG = "MainActivity" ;
    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(MainActivity.this);
        Picasso.get().load("https://p82.cooltext.com/Rendered/Cool%20Text%20-%20Baking%20App%20291237167299492.png").into(recipeAppBannerImage);
        setupMVP();
        getRecipeList();
    }

    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
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
