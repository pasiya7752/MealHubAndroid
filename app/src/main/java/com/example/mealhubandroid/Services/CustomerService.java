package com.example.mealhubandroid.Services;


import com.example.mealhubandroid.Models.CustomerVM;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CustomerService {
    @GET("/findCustomerById")
    Call<CustomerVM> findCustomerById(@Query("customerId") String customerId);
}