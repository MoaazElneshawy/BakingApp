package com.example.moaazfathy.bakingapp.Service;

import com.example.moaazfathy.bakingapp.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MoaazFathy on 05-Feb-18.
 */

public class RetrofitBuilder {
    Retrofit retrofit;

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    OkHttpClient client = new OkHttpClient();

    public RetrofitBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public RetrofitInterfaces.RecipesInterface createRecipesInterface() {
        return retrofit.create(RetrofitInterfaces.RecipesInterface.class);
    }
}
