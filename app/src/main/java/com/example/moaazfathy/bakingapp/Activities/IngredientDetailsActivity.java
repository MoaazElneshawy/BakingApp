package com.example.moaazfathy.bakingapp.Activities;

import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.moaazfathy.bakingapp.Adapters.IngredientsDetailsAdapter;
import com.example.moaazfathy.bakingapp.Constants;
import com.example.moaazfathy.bakingapp.Models.Ingredients;
import com.example.moaazfathy.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IngredientDetailsActivity extends AppCompatActivity {

    @BindView(R.id.ingredient_details)
    RecyclerView mIngredientList;
    @BindView(R.id.ingredient_details_back)
    ImageView mBack;

    IngredientsDetailsAdapter adapter;
    ArrayList<Ingredients> ingredients;
    private Parcelable layoutManagerSavedState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_details);
        ButterKnife.bind(this);
        try {
            ActionBar bar = this.getSupportActionBar();
            bar.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent i = getIntent();

        if (i != null) {
            ingredients = i.getParcelableArrayListExtra(Constants.INGREDIENTS);
        }
        if (savedInstanceState != null) {
            layoutManagerSavedState = savedInstanceState.getParcelable(Constants.LIST_STATE);
        }
        setUpRV();

    }

    private void setUpRV() {
        mIngredientList.setLayoutManager(new LinearLayoutManager(this));
        if (ingredients != null)
            adapter = new IngredientsDetailsAdapter(ingredients, this);
        mIngredientList.setAdapter(adapter);
        if (layoutManagerSavedState != null)
            mIngredientList.getLayoutManager().onRestoreInstanceState(layoutManagerSavedState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.LIST_STATE, mIngredientList.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getParcelable(Constants.LIST_STATE);
    }

    @OnClick(R.id.ingredient_details_back)
    void setmBack(){
        onBackPressed();
    }
}
