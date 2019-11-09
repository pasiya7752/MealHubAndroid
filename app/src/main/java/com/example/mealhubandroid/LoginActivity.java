package com.example.mealhubandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealhubandroid.DTO.Customer.CustomerRequest;
import com.example.mealhubandroid.DTO.Customer.LoginRequest;
import com.example.mealhubandroid.Models.CustomerVM;
import com.example.mealhubandroid.Services.APIUtils;
import com.example.mealhubandroid.Services.UserAuthenticationService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private Button Connect;
    private TextView SignUp;
    CheckBox keepMe;

    boolean isKeepMeChecked=false;
    UserAuthenticationService userAuthenticationService;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        keepMe = (CheckBox) findViewById(R.id.keepLoggedIn);
        Connect=(Button)findViewById(R.id.Connect);
        SignUp=(TextView) findViewById(R.id.signup);


        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConnect();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp();
            }
        });


        userAuthenticationService = APIUtils.getUserAuthenticationService();

        keepMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBoolean();
            }
        });


    }

    public void setBoolean()
    {
        if(keepMe.isChecked())
        {
            isKeepMeChecked = true;
        }
        else
            {
            isKeepMeChecked = false;
        }
    }
    public void onConnect()
    {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.username=username.getText().toString();
        loginRequest.password=password.getText().toString();
        System.out.println(loginRequest.username);
        Call<CustomerVM> call = userAuthenticationService.getAuthentication(loginRequest);
        call.enqueue(new Callback<CustomerVM>() {
            @Override
            public void onResponse(Call<CustomerVM> call, Response<CustomerVM> response) {

                if(response.isSuccessful())
                {
                    if(!response.body().equals(null))
                    {
                        Toast.makeText(LoginActivity.this, "Succesfully Logged in", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                        if(isKeepMeChecked)
                        {
                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putString("username",response.body().getUserName());
                            editor.putString("calorieRequirement",Long.toString(response.body().getDailyCalorieRequirement()));
                            editor.putBoolean("isLoggedIn",true);
                            editor.apply();
                            editor.commit();
                        }

                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<CustomerVM> call, Throwable t) {

            }
        });

    }

    public void onSignUp()
    {
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);

    }
}
