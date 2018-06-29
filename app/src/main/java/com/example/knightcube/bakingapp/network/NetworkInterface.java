package com.example.knightcube.bakingapp.network;

import com.example.knightcube.bakingapp.models.Recipe;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public interface NetworkInterface {

    @GET("raw/7eefa29e95275ae8c5f0823a9f7f3e5c98896e81/BakingAppJson.json")
    Observable<List<Recipe>> getRecipes();

}
