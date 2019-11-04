package com.example.mealhubandroid.DTO.Customer;


import com.example.mealhubandroid.DTO.Base.BaseRequest;
import com.example.mealhubandroid.Models.CustomerVM;

public class LoginRequest extends BaseRequest {
   public String username;
   public String password;
   public boolean isValid;
}
