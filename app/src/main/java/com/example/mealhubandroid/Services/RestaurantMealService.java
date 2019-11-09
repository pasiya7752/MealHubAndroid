package com.example.mealhubandroid.Services;


import com.example.mealhubandroid.DTO.RestaurantMeals.RestaurantMealResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestaurantMealService {

    @POST("/getAllRestaurantMeals")
    Call<RestaurantMealResponse>getAllRestaurantMeals(@Query("restaurantId") String restaurantId);

}