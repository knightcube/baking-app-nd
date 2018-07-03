package com.example.knightcube.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.knightcube.bakingapp.models.Recipe;
import com.example.knightcube.bakingapp.ui.ingredients.IngredientsActivity;
import com.example.knightcube.bakingapp.ui.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by Rajat Kumar Gupta on 02/07/2018.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityBasicTest {
    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickCard_OpensIngredients(){
        // Uses {@link Espresso#onData(org.hamcrest.Matcher)} to get a reference to a specific
        // gridview item and clicks it.
//        onView(withId(R.id.selected_recipe)).check(matches(withText("Brownies")));
        onView(withId(R.id.recipe_recycler_view))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(0, click())
                );
       // Checks that the OrderActivity opens with the correct tea name displayed
//        onView(withId(R.id.selected_recipe)).check(matches(withText("Brownies")));
    }
}
