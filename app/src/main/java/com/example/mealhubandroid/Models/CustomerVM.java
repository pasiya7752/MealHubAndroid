package com.example.mealhubandroid.Models;



import java.math.BigDecimal;

public class CustomerVM {

    String id;
    String firstName;
    String lastName;
    String userName;
    String email;
    int mobile;
    String gender;
    String password;
    int age;
    BigDecimal height;
    BigDecimal weight;
    String activityLevel;
    String healthCondition;
    BigDecimal dailyCalorieRequirement;
    String bmi;

    public CustomerVM() {

    }

    public CustomerVM(String id, String firstName, String lastName, String userName, String email, int mobile, String gender, String password, int age, BigDecimal height, BigDecimal weight, String activityLevel, String healthCondition, BigDecimal dailyCalorieRequirement, String bmi) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.password = password;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.healthCondition = healthCondition;
        this.dailyCalorieRequirement = dailyCalorieRequirement;
        this.bmi = bmi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public BigDecimal getDailyCalorieRequirement() {
        return dailyCalorieRequirement;
    }

    public void setDailyCalorieRequirement(BigDecimal dailyCalorieRequirement) {
        this.dailyCalorieRequirement = dailyCalorieRequirement;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }
}