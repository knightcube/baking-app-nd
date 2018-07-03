package com.example.knightcube.bakingapp.ui.splash;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.knightcube.bakingapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void splashActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//
//        ViewInteraction recyclerView = onView(
//                allOf(withId(R.id.recipe_recycler_view),
//                        childAtPosition(
//                                withClassName(is("android.widget.LinearLayout")),
//                                0)));
//        recyclerView.perform(actionOnItemAtPosition(0, click()));
//
//        ViewInteraction recyclerView2 = onView(
//                allOf(withId(R.id.recipe_recycler_view),
//                        childAtPosition(
//                                withClassName(is("android.widget.LinearLayout")),
//                                0)));
//        recyclerView2.perform(actionOnItemAtPosition(0, click()));
//
//        ViewInteraction recyclerView3 = onView(
//                allOf(withId(R.id.recipe_recycler_view),
//                        childAtPosition(
//                                withClassName(is("android.widget.LinearLayout")),
//                                0)));
//        recyclerView3.perform(actionOnItemAtPosition(1, click()));

//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.ingredients_done_btn), withText("Start cooking"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withClassName(is("android.widget.ScrollView")),
//                                        0),
//                                3)));
//        appCompatButton.perform(scrollTo(), click());

//        ViewInteraction recyclerView4 = onView(
//                allOf(withId(R.id.step_list_rv),
//                        childAtPosition(
//                                withId(R.id.frameLayout),
//                                0)));
//        recyclerView4.perform(actionOnItemAtPosition(0, click()));
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(4941);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction appCompatImageButton = onView(
//                allOf(withContentDescription("Navigate up"),
//                        childAtPosition(
//                                allOf(withId(R.id.detail_toolbar),
//                                        childAtPosition(
//                                                withId(R.id.app_bar),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatImageButton.perform(click());
//
//        ViewInteraction appCompatImageButton2 = onView(
//                allOf(withContentDescription("Navigate up"),
//                        childAtPosition(
//                                allOf(withId(R.id.toolbar),
//                                        childAtPosition(
//                                                withId(R.id.app_bar),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatImageButton2.perform(click());
//
//        ViewInteraction appCompatImageButton3 = onView(
//                allOf(withContentDescription("Navigate up"),
//                        childAtPosition(
//                                allOf(withId(R.id.toolbar),
//                                        childAtPosition(
//                                                withClassName(is("android.support.design.widget.AppBarLayout")),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        appCompatImageButton3.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.recipe_recycler_view),
                                0),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.recipe_name_txt), withText("Brownies"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Brownies")));

        ViewInteraction textView2 = onView(
                allOf(withText("COOK"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        4),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("COOK")));

        ViewInteraction textView3 = onView(
                allOf(withText("COOK"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        4),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("COOK")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
