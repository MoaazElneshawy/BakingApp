package com.example.moaazfathy.bakingapp.Widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.moaazfathy.bakingapp.Constants;
import com.example.moaazfathy.bakingapp.Models.Ingredients;
import com.example.moaazfathy.bakingapp.R;

import java.util.ArrayList;

/**
 * Created by MoaazFathy on 18-Feb-18.
 */

public class WidgetListViewFactory implements RemoteViewsService.RemoteViewsFactory {

    public WidgetListViewFactory(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(Constants.INGREDIENTS, Context.MODE_PRIVATE);
    }

    SharedPreferences preferences;
    Context context;
    ArrayList<Ingredients> ingredients;

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        ingredients = prefReader();
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

    private ArrayList<Ingredients> prefReader() {

        int ingredient_size = preferences.getInt(Constants.INGREDIENTS_SIZE, 0);
        ArrayList<Ingredients> ingredients = new ArrayList<>();

        for (int i = 0; i < ingredient_size; i++) {

            String ingredient = preferences.getString(Constants.INGREDIENT + " " + i, "error");
            float quantity = preferences.getFloat(Constants.QUANTITY + " " + i, 1);
            String measure = preferences.getString(Constants.MEASURE + " " + i, "error");
            Log.e("size", ingredient + " **-** " + quantity+ " **-** " + measure);

            Ingredients ingredientObject = new Ingredients();
            ingredientObject.setIngredient(ingredient);
            ingredientObject.setQuantity(quantity);
            ingredientObject.setMeasure(measure);

            ingredients.add(ingredientObject);
        }

        Log.e("size", ingredients.size() + " **-** " + ingredient_size);
        return ingredients;
    }
}
