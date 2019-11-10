package com.example.mealhubandroid.Models;

public class ReviewVM {


    String id;
    String customerId;
    String customerName;
    String restaurantId;
    String review;
    float healthRate;


    public ReviewVM(String id, String customerId, String customerName, String restaurantId, String review, float healthRate) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.restaurantId = restaurantId;
        this.review = review;
        this.healthRate = healthRate;
    }

    public ReviewVM()
    {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getHealthRate() {
        return healthRate;
    }

    public void setHealthRate(float healthRate) {
        this.healthRate = healthRate;
    }
}
