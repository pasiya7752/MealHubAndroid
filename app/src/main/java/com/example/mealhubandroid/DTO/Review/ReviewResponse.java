package com.example.mealhubandroid.DTO.Review;


import com.example.mealhubandroid.DTO.Base.BaseResponse;
import com.example.mealhubandroid.Models.ReviewVM;

import java.util.List;

public class ReviewResponse extends BaseResponse {
    public ReviewVM reviewVM;
    public List<ReviewVM> reviewVMS;
}
