package com.example.moaazfathy.bakingapp.Activities;

import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.moaazfathy.bakingapp.Constants;
import com.example.moaazfathy.bakingapp.Fragments.IngredientDetailsFragment;
import com.example.moaazfathy.bakingapp.Fragments.IngredientFragment;
import com.example.moaazfathy.bakingapp.Fragments.StepDetailsFragment;
import com.example.moaazfathy.bakingapp.Fragments.StepsFragment;
import com.example.moaazfathy.bakingapp.Models.Ingredients;
import com.example.moaazfathy.bakingapp.Models.Steps;
import com.example.moaazfathy.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends AppCompatActivity
        implements IngredientFragment.OnIngredientClickListener, StepsFragment.OnStepClickListener {

    @BindView(R.id.recipe_toolbar)
    TextView mRecipeToolbar;
    @BindView(R.id.recipe_ingredient_container)
    FrameLayout mRecipeIngredientsFrame;
    @BindView(R.id.recipe_steps_container)
    FrameLayout mRecipeStepsFrame;

    ArrayList<Steps> steps;
    ArrayList<Ingredients> ingredients;
    IngredientFragment ingredientFragment;
    StepsFragment stepsFragment;
    IngredientDetailsFragment ingredientDetailsFragment;
    StepDetailsFragment stepDetailsFragment;
    FragmentManager manager;

    boolean twoPaneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        ButterKnife.bind(this);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.hide();

        initialFragments();
        Intent intent = this.getIntent();

        if (intent != null) {
            mRecipeToolbar.setText(intent.getStringExtra(Constants.RECIPE));
            steps = intent.getParcelableArrayListExtra(Constants.STEPS);
            ingredients = intent.getParcelableArrayListExtra(Constants.INGREDIENTS);
            stepsFragment.setSteps(steps);
            ingredientFragment.setIngredients(ingredients);
        }
        if (savedInstanceState != null) {
            steps = savedInstanceState.getParcelableArrayList(Constants.STEPS);
            ingredients = savedInstanceState.getParcelableArrayList(Constants.INGREDIENTS);
            stepsFragment.setSteps(steps);
            ingredientFragment.setIngredients(ingredients);
        }

        if (findViewById(R.id.details_container) != null) {
            twoPaneMode = true;
        }

    }

    private void initialFragments() {
        ingredientFragment = new IngredientFragment();
        stepsFragment = new StepsFragment();

        ingredientDetailsFragment = new IngredientDetailsFragment();
        stepDetailsFragment = new StepDetailsFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.recipe_ingredient_container, ingredientFragment, "ingredientFragment")
                .add(R.id.recipe_steps_container, stepsFragment, "stepsFragment")
                .commit();
    }

    @Override
    public void onIngredientClicked(ArrayList<Ingredients> ingredients) {
        if (twoPaneMode) {
            ingredientDetailsFragment.setIngredients(ingredients);
            manager.beginTransaction().replace(R.id.details_container, ingredientDetailsFragment)
                    .commit();
        } else {
            if (ingredientDetailsFragment != null) {
                ingredientDetailsFragment.setIngredients(ingredients);
                manager.beginTransaction().replace(R.id.recipe_ingredient_container, ingredientDetailsFragment)
                        .addToBackStack("ingredientFragment")
                        .commit();
            }
        }
    }


    @Override
    public void onStepClicked(String description, String video,String thumbnail) {
        if (stepDetailsFragment != null) {
            if (twoPaneMode) {
                stepDetailsFragment.setVideo(video);
                stepDetailsFragment.setDescription(description);
                manager.beginTransaction().replace(R.id.details_container, stepDetailsFragment, "stepDetailsFragment")
                        .detach(stepDetailsFragment)
                        .attach(stepDetailsFragment)
                        .commit();
            } else {
                Intent intent = new Intent(RecipeDetailsActivity.this, StepDetailsActivity.class);
                intent.putExtra(Constants.VIDEO, video);
                intent.putExtra(Constants.DESCRIPTION, description);
                intent.putExtra(Constants.THUMBNAIL, thumbnail);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.STEPS, steps);
        outState.putParcelableArrayList(Constants.INGREDIENTS, ingredients);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getParcelableArrayList(Constants.STEPS);
        savedInstanceState.getParcelableArrayList(Constants.INGREDIENTS);
    }

}