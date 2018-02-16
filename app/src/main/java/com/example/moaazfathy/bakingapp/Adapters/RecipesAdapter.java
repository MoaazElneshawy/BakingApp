package com.example.moaazfathy.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moaazfathy.bakingapp.Models.Recipes;
import com.example.moaazfathy.bakingapp.R;
import com.squareup.picasso.Picasso;

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
        if (!(recipes.get(position).getImage() == null || TextUtils.isEmpty(recipes.get(position).getImage()))) {
            switch (recipeTitle) {
                case "Nutella Pie":
                    Picasso.with(context)
                            .load(recipes.get(position).getImage())
                            .error(R.drawable.neutella_pie)
                            .into(holder.mRecipeImage);
                    break;
                case "Brownies":
                    Picasso.with(context)
                            .load(recipes.get(position).getImage())
                            .error(R.drawable.brownies)
                            .into(holder.mRecipeImage);
                    break;
                case "Yellow Cake":
                    Picasso.with(context)
                            .load(recipes.get(position).getImage())
                            .error(R.drawable.yellow_cake)
                            .into(holder.mRecipeImage);
                    break;
                case "Cheesecake":
                    Picasso.with(context)
                            .load(recipes.get(position).getImage())
                            .error(R.drawable.cheescake)
                            .into(holder.mRecipeImage);
                    break;
            }
        } else {
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
        }

        holder.mRecipeTitle.setText(recipeTitle);
    }

    @Override
    public int getItemCount() {

        if ((recipes != null) && recipes.size() > 0)
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
