package com.example.moaazfathy.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import com.example.moaazfathy.bakingapp.Activities.MainActivity;

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


}
