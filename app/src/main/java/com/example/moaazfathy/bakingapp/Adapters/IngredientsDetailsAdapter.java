package com.example.moaazfathy.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moaazfathy.bakingapp.Models.Ingredients;
import com.example.moaazfathy.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MoaazFathy on 05-Feb-18.
 */

public class IngredientsDetailsAdapter extends RecyclerView.Adapter<IngredientsDetailsAdapter.IngredientsViewHolder> {

    List<Ingredients> ingredients;
    Context context;

    public IngredientsDetailsAdapter(List<Ingredients> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        holder.mIngredientTitle.setText(ingredients.get(position).getIngredient());
        holder.mIngredientQuantity.append(" : " + String.valueOf(ingredients.get(position).getQuantity()) + " " + ingredients.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        if (ingredients.size() > 0)
            return ingredients.size();
        else
            return 0;
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_ingredient_title)
        TextView mIngredientTitle;
        @BindView(R.id.item_ingredient_quantity)
        TextView mIngredientQuantity;


        public IngredientsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
