package com.example.moaazfathy.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moaazfathy.bakingapp.Models.Recipes;
import com.example.moaazfathy.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MoaazFathy on 04-Feb-18.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    public RecipesAdapter(List<Recipes> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
    }

    List<Recipes> recipes;
    Context context;

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        String recipeTitle = recipes.get(position).getName();
        switch (recipeTitle) {
            case "Nutella Pie":
                holder.mRecipeImage.setImageResource(R.drawable.neutella_pie);
                break;
            case "Brownies":
                holder.mRecipeImage.setImageResource(R.drawable.brownies);
                break;
            case "Yellow Cake":
                holder.mRecipeImage.setImageResource(R.drawable.yellow_cake);
                break;
            case "Cheesecake":
                holder.mRecipeImage.setImageResource(R.drawable.cheescake);
                break;
        }
        holder.mRecipeTitle.setText(recipeTitle);
    }

    @Override
    public int getItemCount() {

        if ((recipes!= null) && recipes.size() > 0)
            return recipes.size();
        else
            return 0;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_recipe_image)
        ImageView mRecipeImage;
        @BindView(R.id.item_recipe_title)
        TextView mRecipeTitle;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
