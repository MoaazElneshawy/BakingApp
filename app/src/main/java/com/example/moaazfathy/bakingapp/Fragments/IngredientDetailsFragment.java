package com.example.moaazfathy.bakingapp.Fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moaazfathy.bakingapp.Adapters.IngredientsDetailsAdapter;
import com.example.moaazfathy.bakingapp.Constants;
import com.example.moaazfathy.bakingapp.Models.Ingredients;
import com.example.moaazfathy.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MoaazFathy on 07-Feb-18.
 */

public class IngredientDetailsFragment extends Fragment {

    @BindView(R.id.ingredient_details_rv)
    RecyclerView mIngredientDetailsRV;

    IngredientsDetailsAdapter adapter;
    ArrayList<Ingredients> ingredients;
    private Parcelable layoutManagerSavedState;

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient_details, container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState != null) {
            layoutManagerSavedState = savedInstanceState.getParcelable(Constants.LIST_STATE);
        }
        setUpRV();
        return view;
    }

    private void setUpRV() {
        mIngredientDetailsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (ingredients != null)
            adapter = new IngredientsDetailsAdapter(ingredients, getActivity());
        mIngredientDetailsRV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (layoutManagerSavedState != null)
            mIngredientDetailsRV.getLayoutManager().onRestoreInstanceState(layoutManagerSavedState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.LIST_STATE, mIngredientDetailsRV.getLayoutManager().onSaveInstanceState());
    }
}
