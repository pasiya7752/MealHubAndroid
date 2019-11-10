package com.example.mealhubandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealhubandroid.DTO.Customer.CustomerRequest;
import com.example.mealhubandroid.Models.CustomerVM;
import com.example.mealhubandroid.Services.APIUtils;
import com.example.mealhubandroid.Services.SignUpService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateHealthProfileActivity extends AppCompatActivity {

    Button create;
    CustomerVM customerVM = new CustomerVM();
    SignUpService signUpService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_health_profile);

        customerVM= new Gson().fromJson(getIntent().getSerializableExtra("customerVM").toString(), new TypeToken<CustomerVM>(){}.getType());

        create=(Button)findViewById(R.id.signupbtn);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProfileCreate();
            }
        });

        signUpService = APIUtils.getSignUpService();

    }

    public void onProfileCreate()
    {
        EditText age = (EditText) findViewById(R.id.age);
        EditText height = (EditText) findViewById(R.id.height);
        EditText weight = (EditText) findViewById(R.id.weight);
        Spinner gender = (Spinner) findViewById(R.id.gender);
        Spinner activity = (Spinner) findViewById(R.id.activity_level);
        Spinner healthConditions = (Spinner) findViewById(R.id.health_conditions);

        int age1 = Integer.parseInt(age.getText().toString());
        BigDecimal height1 = new BigDecimal (height.getText().toString());
        BigDecimal weight1 = new BigDecimal (weight.getText().toString());
        String gender1 = gender.getSelectedItem().toString();
        String activity1 = activity.getSelectedItem().toString();
        String healthConditions1 = healthConditions.getSelectedItem().toString();
        String bmi;
        BigDecimal resultedCalorieCount = new BigDecimal(0);
        BigDecimal activityLevel = new BigDecimal(0);

        switch (activity1)
        {
            case "SEDENTARY":
                activityLevel = BigDecimal.valueOf(1.2);
                break;
            case"SLIGHTLY":
                activityLevel = BigDecimal.valueOf(1.4);
                break;
            case"MODERATELY":
                activityLevel = BigDecimal.valueOf(1.6);
                break;
            case"VERY_ACTIVE":
                activityLevel = BigDecimal.valueOf(1.75);
                break;
            case"EXTRA_ACTIVE":
                activityLevel = BigDecimal.valueOf(2);
                break;
            case"PROFESSIONAL_ATHLETE":
                activityLevel = BigDecimal.valueOf(2.3);
                break;
        }



        resultedCalorieCount=calculateCalorieLimit(gender1,weight1,height1,age1,activityLevel);
        bmi = calculateBMI(weight1,height1);
        System.out.println("Daily Calorie Requirement = "+resultedCalorieCount);



        customerVM.setAge(age1);
        customerVM.setHeight(height1);
        customerVM.setWeight(weight1);
        customerVM.setGender(gender1);
        customerVM.setHealthCondition(healthConditions1);
        customerVM.setDailyCalorieRequirement(resultedCalorieCount);
        customerVM.setBmi(bmi);
        customerVM.setActivityLevel(activity1);

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.customerVM = customerVM;

        Call<CustomerRequest> call = signUpService.SignUp(customerRequest);
        call.enqueue(new Callback<CustomerRequest>() {
            @Override
            public void onResponse(Call<CustomerRequest> call, Response<CustomerRequest> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(CreateHealthProfileActivity.this, "Successfully signed up!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateHealthProfileActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CustomerRequest> call, Throwable t) {
                Toast.makeText(CreateHealthProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public BigDecimal calculateCalorieLimit(String gender, BigDecimal weight, BigDecimal height, int age, BigDecimal activityLevel) {

        if (gender.equals("Female")) {
            //should return 1,172.75 for w-48 h-155 age-23
            return (weight.multiply(BigDecimal.valueOf(10)).add(height.multiply(BigDecimal.valueOf(6.25))))
                    .subtract(BigDecimal.valueOf(age*5.0)).subtract(BigDecimal.valueOf(161)).multiply(activityLevel);
        }
        return (weight.multiply(BigDecimal.valueOf(10)).add(height.multiply(BigDecimal.valueOf(6.25))))
                .subtract(BigDecimal.valueOf(age*5.0)).add(BigDecimal.valueOf(5)).multiply(activityLevel);
    }

    public String calculateBMI(BigDecimal weight, BigDecimal height) {
        BigDecimal bmiValue = weight.divide((height.divide(BigDecimal.valueOf(100)).pow(2)), 3);
        if (bmiValue.floatValue()<18.5){
            return "Underweight";
        }
        else if (bmiValue.floatValue()<=23.0){
            return "Normal";
        }
        return "Overweight";
    }

}
