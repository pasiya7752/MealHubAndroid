package com.example.mealhubandroid.Models;


import java.math.BigDecimal;

public class NutritionDataVM {

    long id;
    String foodName;
    String measurement;
    BigDecimal calories;
    BigDecimal fat;
    BigDecimal carbs;
    BigDecimal protein;

    public NutritionDataVM(long id, String foodName, String measurement, BigDecimal calories, BigDecimal fat, BigDecimal carbs, BigDecimal protein) {
        this.id = id;
        this.foodName = foodName;
        this.measurement = measurement;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public BigDecimal getCarbs() {
        return carbs;
    }

    public void setCarbs(BigDecimal carbs) {
        this.carbs = carbs;
    }

    public BigDecimal getProtein() {
        return protein;
    }

    public void setProtein(BigDecimal protein) {
        this.protein = protein;
    }
}
