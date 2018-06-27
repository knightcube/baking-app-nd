package com.example.knightcube.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String recipe, List<Ingredient> ingredients) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredients_widget);
        views.removeAllViews(R.id.widget_ingredients_container);

        Intent launchMain = new Intent(context, MainActivity.class);
        PendingIntent pendingMainIntent = PendingIntent.getActivity(context, 0, launchMain, 0);
        views.setOnClickPendingIntent(R.id.widget_ingredients_container, pendingMainIntent);

        views.setTextViewText(R.id.appwidget_text,recipe);
        for (Ingredient ingredient : ingredients) {
            RemoteViews ingredientView = new RemoteViews(context.getPackageName(), R.layout.list_item_recipes);
            ingredientView.setTextViewText(R.id.recipe_name_txt,
                    String.valueOf(ingredient.getIngredient()) + "\n");
            views.addView(R.id.widget_ingredients_container, ingredientView);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        prefs = context.getSharedPreferences("FAVOURITES",Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("favourite_recipe","");
        Recipe recipe = gson.fromJson(json,Recipe.class);

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId,recipe.getName(),recipe.getIngredients());
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
}

