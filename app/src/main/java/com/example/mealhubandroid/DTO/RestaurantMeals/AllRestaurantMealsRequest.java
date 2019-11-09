package com.example.mealhubandroid.DTO.RestaurantMeals;

public class AllRestaurantMealsRequest {
    public String restaurantId;

    public AllRestaurantMealsRequest(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
