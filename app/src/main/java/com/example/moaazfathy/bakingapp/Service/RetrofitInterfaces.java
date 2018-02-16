package com.example.moaazfathy.bakingapp.Service;

import com.example.moaazfathy.bakingapp.Models.Recipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by MoaazFathy on 05-Feb-18.
 */

public class RetrofitInterfaces {
    public interface RecipesInterface {
        @GET("baking.json")
        Call<List<Recipes>> getRecipes();
    }
}
