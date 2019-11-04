package com.example.mealhubandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mealhubandroid.DTO.Customer.CustomerRequest;
import com.example.mealhubandroid.DTO.Customer.CustomerResponse;
import com.example.mealhubandroid.Models.CustomerVM;
import com.example.mealhubandroid.Models.MealVM;
import com.example.mealhubandroid.Models.NutritionDataVM;
import com.example.mealhubandroid.Services.APIUtils;
import com.example.mealhubandroid.Services.FileService;
import com.example.mealhubandroid.Services.SignUpApiService;
import com.example.mealhubandroid.Services.SignUpService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import okhttp3.MultipartBody;
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
        String healthConditions1 = healthConditions.getSelectedItem().toString();
        boolean gend;
        long resultedCalorieCount;

        if(gender1=="Male")
        {
            gend=true;
        }
        else
        {
            gend=false;
        }

        resultedCalorieCount=calculate(age1,height1,weight1,gend);

        System.out.println("Daily Calorie Requirement = "+resultedCalorieCount);



        customerVM.setAge(age1);
        customerVM.setHeight(height1);
        customerVM.setWeight(weight1);
        customerVM.setGender(gender1);
        customerVM.setHealthCondition(healthConditions1);
        customerVM.setDailyCalorieRequirement(resultedCalorieCount);

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

    public long calculate(int age, BigDecimal height, BigDecimal weight, boolean male) {



        if (male) {
            //66.5 + 13.8 x (Weight in kg) + 5 x (Height in cm); 6.8 x age/
            return Math.round((66.5 + (13.8 * weight.floatValue()) + (5 * height.floatValue()) - (6.8 * age))*1.76);
        }

        else {
            /* 655.1 + 9.6 x (Weight in kg) + 1.9 x (Height in cm); 4.7 x age*/
            return Math.round((655.1 + (9.6 * weight.floatValue()) + (1.9 * height.floatValue()) - (4.7 * age))*1.76);
        }
    }

}
