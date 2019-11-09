package com.example.mealhubandroid.DTO.RestaurantMeals;


import com.example.mealhubandroid.DTO.Base.BaseResponse;
import com.example.mealhubandroid.Models.RestaurantMealVM;

import java.util.List;

public class RestaurantMealResponse extends BaseResponse {
   public RestaurantMealVM restaurantMealVM;
   public List<RestaurantMealVM> restaurantMealVMS;
}
