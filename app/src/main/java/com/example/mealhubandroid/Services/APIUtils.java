package com.example.mealhubandroid.Services;

import com.example.mealhubandroid.helper.NetworkClient;

public class APIUtils {

    private APIUtils()
    {}

    public static final String API_URL = "http://192.168.42.253:8080";

    public static FileService getFileService()
    {
        return NetworkClient.getRetrofitClient(API_URL).create(FileService.class);
    }

    public static SignUpService getSignUpService()
    {
        return NetworkClient.getRetrofitClient(API_URL).create(SignUpService.class);
    }

    public static UserAuthenticationService getUserAuthenticationService()
    {
        return NetworkClient.getRetrofitClient(API_URL).create(UserAuthenticationService.class);
    }

    public static RestaurantMealService getRestaurantMealService()
    {
        return NetworkClient.getRetrofitClient(API_URL).create(RestaurantMealService.class);
    }

    public static ReviewService getReviewService()
    {
        return NetworkClient.getRetrofitClient(API_URL).create(ReviewService.class);
    }

    public static CustomerService getCustomerService()
    {
        return NetworkClient.getRetrofitClient(API_URL).create(CustomerService.class);
    }
    public static FatSecretService getFatSecretService()
    {
        return NetworkClient.getRetrofitClient(API_URL).create(FatSecretService.class);
    }

}
