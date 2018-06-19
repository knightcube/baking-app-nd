package com.example.knightcube.bakingapp.network;

import com.example.knightcube.bakingapp.models.Recipe;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Rajat Kumar Gupta on 19/06/2018.
 */
public interface NetworkInterface {

    @GET("59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipes();

}
