package com.example.moaazfathy.bakingapp.Widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.moaazfathy.bakingapp.Activities.RecipeDetailsActivity;
import com.example.moaazfathy.bakingapp.Models.Ingredients;
import com.example.moaazfathy.bakingapp.R;

import java.util.ArrayList;

/**
 * Created by MoaazFathy on 18-Feb-18.
 */

public class WidgetListViewFactory implements RemoteViewsService.RemoteViewsFactory {

    public WidgetListViewFactory(Context context) {
        this.context = context;
    }

    Context context;
    ArrayList<Ingredients> ingredients;

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        ingredients = RecipeDetailsActivity.ingredients;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (ingredients.size() == 0 || ingredients == null)
            return 0;
        else
            return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item_list);
        remoteViews.setTextViewText(R.id.widget_item_ingredient, ingredients.get(i).getIngredient());
        remoteViews.setTextViewText(R.id.widget_item_quantity, ingredients.get(i).getQuantity() + " " + ingredients.get(i).getMeasure());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
