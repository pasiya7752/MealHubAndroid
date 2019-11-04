package com.example.mealhubandroid.Services;


import com.example.mealhubandroid.DTO.Customer.LoginRequest;
import com.example.mealhubandroid.Models.CustomerVM;
import com.example.mealhubandroid.Models.NutritionDataVM;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserAuthenticationService {
    @POST("/authenticateUser")
    Call<CustomerVM> getAuthentication(@Body LoginRequest loginRequest);


}