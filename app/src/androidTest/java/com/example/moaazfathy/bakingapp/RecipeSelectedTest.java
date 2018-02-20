package com.example.moaazfathy.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import com.example.moaazfathy.bakingapp.Activities.MainActivity;
import com.example.moaazfathy.bakingapp.Activities.RecipeDetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by MoaazFathy on 14-Feb-18.
 */

@RunWith(AndroidJUnit4.class)
public class RecipeSelectedTest {

    @Rule
    public ActivityTestRule<MainActivity> mRecipeTest
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onStartTheMain() {
// find the view
        onView(withId(R.id.main_toolbar_text))
                // check matches
                .check(matches(withText("Recipes")));
    }


    @Test
    public void onRecipeItemClicked_OpenRecipeDetails() {
        onView(withId(R.id.recipes_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(RecipeDetailsActivity.class.getName()));

        onView(withId(R.id.recipes_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        intended(hasComponent(RecipeDetailsActivity.class.getName()));

        onView(withId(R.id.recipes_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        intended(hasComponent(RecipeDetailsActivity.class.getName()));

        onView(withId(R.id.recipes_rv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        intended(hasComponent(RecipeDetailsActivity.class.getName()));
    }


    @Test
    public void onLoadFinished_DisplaysRecipesRecyclerView() {
        onView(withId(R.id.recipes_rv)).check(matches(isCompletelyDisplayed()));
    }


}
