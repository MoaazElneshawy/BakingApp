package com.example.moaazfathy.bakingapp.Activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.chootdev.recycleclick.RecycleClick;
import com.example.moaazfathy.bakingapp.Adapters.RecipesAdapter;
import com.example.moaazfathy.bakingapp.Constants;
import com.example.moaazfathy.bakingapp.Models.Ingredients;
import com.example.moaazfathy.bakingapp.Models.Recipes;
import com.example.moaazfathy.bakingapp.Models.Steps;
import com.example.moaazfathy.bakingapp.R;
import com.example.moaazfathy.bakingapp.Service.RetrofitBuilder;
import com.example.moaazfathy.bakingapp.Service.RetrofitInterfaces;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recipes_rv)
    RecyclerView mRecipesRV;
    List<Recipes> recipes;
    List<Steps> steps;
    List<Ingredients> ingredients;
    RecipesAdapter adapter;

    private Parcelable layoutManagerSavedState;
    boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.hide();
        ButterKnife.bind(this);
        recipes = new ArrayList<>();
        steps = new ArrayList<>();
        ingredients = new ArrayList<>();
        if (findViewById(R.id.main_twoPane) != null) {
            mRecipesRV.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        } else {
            mRecipesRV.setLayoutManager(new LinearLayoutManager(this));

        }
        if (savedInstanceState != null) {
            recipes = savedInstanceState.getParcelableArrayList(Constants.RECIPE);
            layoutManagerSavedState = savedInstanceState.getParcelable(Constants.LIST_STATE);
            initialRecyclerView(recipes);
            mProgressBar.setVisibility(View.GONE);
        } else {
            recipesRequest();
        }
    }

    private void initialRecyclerView(List recipes) {
        final List<Recipes> mRecipes = recipes;
        adapter = new RecipesAdapter(mRecipes, this);
        mRecipesRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        onRestoreScrolling();
        RecycleClick.addTo(mRecipesRV).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(MainActivity.this, RecipeDetailsActivity.class);
                intent.putExtra(Constants.RECIPE, mRecipes.get(position).getName());
                intent.putParcelableArrayListExtra(Constants.INGREDIENTS, (ArrayList<? extends Parcelable>) mRecipes.get(position).getIngredients());
                intent.putParcelableArrayListExtra(Constants.STEPS, (ArrayList<? extends Parcelable>) mRecipes.get(position).getSteps());
                startActivity(intent);
            }
        });

    }

    private void recipesRequest() {

        RetrofitBuilder builder = new RetrofitBuilder();
        RetrofitInterfaces.RecipesInterface recipesInterface = builder.createRecipesInterface();
        Call<List<Recipes>> recipesCall = recipesInterface.getRecipes();
        recipesCall.enqueue(new Callback<List<Recipes>>() {
            @Override
            public void onResponse(Call<List<Recipes>> call, Response<List<Recipes>> response) {
                Log.e("connection", "success");
                if (response.body() != null) {
                    recipes = response.body();
                    mProgressBar.setVisibility(View.GONE);
                    initialRecyclerView(recipes);

                }
            }

            @Override
            public void onFailure(Call<List<Recipes>> call, Throwable t) {
                Log.e("connection", "failure" + " -- " + t.getMessage());
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.RECIPE, (ArrayList<? extends Parcelable>) recipes);
        outState.putParcelable(Constants.LIST_STATE,mRecipesRV.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getParcelableArrayList(Constants.RECIPE);
        savedInstanceState.getParcelable(Constants.LIST_STATE);
    }

    private void onRestoreScrolling(){
        if (layoutManagerSavedState != null){
            mRecipesRV.getLayoutManager().onRestoreInstanceState(layoutManagerSavedState);
        }
    }
}
