package com.example.mealhubandroid.Services;


import com.example.mealhubandroid.DTO.FatSecret.FoodNutritionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FatSecretService {

    @GET("/searchFoods")
    Call<FoodNutritionResponse> searchFoods(@Query("query") String query );


}