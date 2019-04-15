package com.mistershorr.retrofittest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gshorr on 1/10/18.
 */

public interface FoodApi {
    String BASE_URL = "http://www.recipepuppy.com/";

    @GET("api")
    Call<RecipeResponse> getRecipes(@Query("i") String ingredients, @Query("q") String search);
}
