package com.example.mealhubandroid.Models;

import java.math.BigDecimal;

public class MealVM {

    String id;
    String category;
    String foodName;
    double price;
    String ingredients[];
    String portions[];
    BigDecimal totalCalories;
    BigDecimal totalFat;
    BigDecimal totalCarbs;
    BigDecimal totalProtein;
    String imagePath;



    public MealVM(String category, String foodName, double price, String[] ingredients, String[] portions, BigDecimal totalCalories, BigDecimal totalFat, BigDecimal totalCarbs, BigDecimal totalProtein, String imagePath) {
        this.category = category;
        this.foodName = foodName;
        this.price = price;
        this.ingredients = ingredients;
        this.portions = portions;
        this.totalCalories = totalCalories;
        this.totalFat = totalFat;
        this.totalCarbs = totalCarbs;
        this.totalProtein = totalProtein;
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(BigDecimal totalCalories) {
        this.totalCalories = totalCalories;
    }

    public BigDecimal getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(BigDecimal totalFat) {
        this.totalFat = totalFat;
    }

    public BigDecimal getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(BigDecimal totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public BigDecimal getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(BigDecimal totalProtein) {
        this.totalProtein = totalProtein;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getPortions() {
        return portions;
    }

    public void setPortions(String[] portions) {
        this.portions = portions;
    }
}
