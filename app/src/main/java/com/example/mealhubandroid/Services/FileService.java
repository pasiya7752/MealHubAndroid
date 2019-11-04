package com.example.mealhubandroid.Services;


import com.example.mealhubandroid.DTO.Customer.CustomerRequest;
import com.example.mealhubandroid.DTO.Customer.CustomerResponse;
import com.example.mealhubandroid.Models.FileInfo;
import com.example.mealhubandroid.Models.NutritionDataVM;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {
    @Multipart
    @POST("/MenuImageReader")
    Call<List<NutritionDataVM>> upload(@Part MultipartBody.Part file);


}