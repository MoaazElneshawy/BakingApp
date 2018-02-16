package com.example.moaazfathy.bakingapp.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.moaazfathy.bakingapp.Adapters.IngredientsDetailsAdapter;
import com.example.moaazfathy.bakingapp.Constants;
import com.example.moaazfathy.bakingapp.Models.Ingredients;
import com.example.moaazfathy.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientDetailsActivity extends AppCompatActivity {

    @BindView(R.id.ingredients_details_rv)
    RecyclerView mIngredients;

    ArrayList<Ingredients> ingredients;
    IngredientsDetailsAdapter adapter;
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

        Intent intent = this.getIntent();
        if (intent != null){
            ingredients = intent.getParcelableArrayListExtra(Constants.INGREDIENTS);
            setUpRV();
        }

    }
    private void setUpRV() {
        mIngredients.setLayoutManager(new LinearLayoutManager(this));
        if (ingredients != null)
            adapter = new IngredientsDetailsAdapter(ingredients, this);
        mIngredients.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
