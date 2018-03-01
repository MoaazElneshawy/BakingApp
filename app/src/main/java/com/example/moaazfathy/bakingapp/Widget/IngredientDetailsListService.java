package com.example.moaazfathy.bakingapp.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;

import com.example.moaazfathy.bakingapp.Activities.RecipeDetailsActivity;
import com.example.moaazfathy.bakingapp.Constants;
import com.example.moaazfathy.bakingapp.R;

public class IngredientDetailsListService extends IntentService {

    public IngredientDetailsListService() {
        super("IngredientDetailsListService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (Constants.INGREDIENT_SERVICE_ACTION.equals(action)) {
                handleActionChangeIngredientList();
            }
        }
    }

    public static boolean startActionChangeIngredientList(Context context) {
        Intent intent = new Intent(context, IngredientDetailsListService.class);
        intent.setAction(Constants.INGREDIENT_SERVICE_ACTION);

        // a temporary solution for Android 8.0
        try {
            context.startService(intent);
            return true;
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void handleActionChangeIngredientList() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidgetProvider.class));
        RecipeWidgetProvider.updateIngredientWidgets(this, appWidgetManager, appWidgetIds, RecipeDetailsActivity.title, RecipeDetailsActivity.INGREDIENTS);
    }
}
