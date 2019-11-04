package com.example.mealhubandroid.DTO.Customer;



import com.example.mealhubandroid.DTO.Base.BaseResponse;
import com.example.mealhubandroid.Models.CustomerVM;

import java.util.List;

public class CustomerResponse extends BaseResponse {

    public List<CustomerVM> customerVMS;
    public CustomerVM customerVM;

}
