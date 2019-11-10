package com.example.mealhubandroid.Services;


import com.example.mealhubandroid.DTO.Review.ReviewRequest;
import com.example.mealhubandroid.DTO.Review.ReviewResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ReviewService {
    @POST("/addReview")
    Call<ReviewResponse>addReview(@Body ReviewRequest reviewRequest);

    @POST("/getAllRestaurantReviews")
    Call<ReviewResponse> getAllReviews(@Query("restaurantId") String restaurantId);

}