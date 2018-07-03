package com.example.knightcube.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.knightcube.bakingapp.R;
import com.example.knightcube.bakingapp.models.Ingredient;
import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.ui.main.MainActivity;
import com.google.gson.Gson;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeIngredientsWidget extends AppWidgetProvider {

    private static SharedPreferences prefs;
    private RemoteViews views;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them

        for (int appWidgetId : appWidgetIds) {
            views = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredients_widget);
            appWidgetManager.updateAppWidget(appWidgetId, views);

            Intent configIntent = new Intent(context, MainActivity.class);
            PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);
            views.setOnClickPendingIntent(R.id.start_cooking_btn_wgt, configPendingIntent);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.i("IngredientWidget", "onReceive: called");
        String action = intent.getAction();
        views = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredients_widget);
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mainActivityIntent, 0);
        views.setOnClickPendingIntent(R.id.start_cooking_btn_wgt, pendingIntent);

        views.setTextViewText(R.id.recipe_selected_wgt, getRecipe(context).getName());
        List<Ingredient> ingredientList = getRecipe(context).getIngredients();
        StringBuilder builder = new StringBuilder();
        int pos = 1;
        for (Ingredient currentIngredients : ingredientList) {
            builder.append(pos + "." + currentIngredients.getIngredient() + " " + currentIngredients.getQuantity() + " " + currentIngredients.getMeasure() + "\n");
            pos++;
        }

        views.setTextViewText(R.id.ingredients_text_wgt, builder.toString());

        Log.i("IngredientWidget", "onReceive: " + builder.toString());
        //Now update all widgets
        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, RecipeIngredientsWidget.class), views);


    }

    public Recipe getRecipe(Context context) {
        prefs = context.getSharedPreferences("FAVOURITES", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("favourite_recipe", "");
        Recipe recipe = gson.fromJson(json, Recipe.class);
        return recipe;
    }
}

