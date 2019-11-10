package com.example.mealhubandroid;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealhubandroid.DTO.FatSecret.FoodNutritionResponse;
import com.example.mealhubandroid.Models.NutritionDataVM;
import com.example.mealhubandroid.Services.APIUtils;
import com.example.mealhubandroid.Services.FatSecretService;
import com.example.mealhubandroid.Services.NutritionAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogFoodActivity extends AppCompatActivity {

    ArrayList<NutritionDataVM> nutritionDataVMS= new ArrayList<>();
    FatSecretService fatSecretService;
    AutoCompleteTextView searchFood;
    NutritionAdapter adapter;
    private NutritionDataVM selectedPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_food);


        searchFood = (AutoCompleteTextView) findViewById(R.id.searchFood);

        searchFood.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchFoods();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fatSecretService = APIUtils.getFatSecretService();

    }


    public void searchFoods()
    {

        Call<FoodNutritionResponse> call = fatSecretService.searchFoods(searchFood.getText().toString());

        call.enqueue(new Callback<FoodNutritionResponse>() {
            @Override
            public void onResponse(Call<FoodNutritionResponse> call, Response<FoodNutritionResponse> response) {
                nutritionDataVMS = (ArrayList<NutritionDataVM>) response.body().nutritionDataVM;
                adapter = new NutritionAdapter(LogFoodActivity.this, R.layout.activity_log_food, R.id.lbl_name, nutritionDataVMS);
                searchFood.setAdapter(adapter);
                searchFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                        //this is the way to find selected object/item
                        selectedPerson = (NutritionDataVM) adapterView.getItemAtPosition(pos);
                    }
                });
            }

            @Override
            public void onFailure(Call<FoodNutritionResponse> call, Throwable t) {

            }
        });
    }




}
