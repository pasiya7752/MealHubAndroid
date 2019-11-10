package com.example.mealhubandroid.DTO.Review;


import com.example.mealhubandroid.DTO.Base.BaseRequest;
import com.example.mealhubandroid.Models.ReviewVM;

import java.util.List;

public class ReviewRequest  extends BaseRequest {
    public ReviewVM reviewVM;
    public List<ReviewVM> reviewVMS;
}
