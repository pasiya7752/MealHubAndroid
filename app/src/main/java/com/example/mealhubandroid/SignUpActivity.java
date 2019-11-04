package com.example.mealhubandroid;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealhubandroid.Models.CustomerVM;
import com.google.gson.Gson;

public class SignUpActivity extends AppCompatActivity {


    private TextView SignIn;
    private Button nextbtn;
    private EditText fname;
    private EditText lname;
    private EditText uname;
    private EditText email;
    private EditText mobile;
    private EditText password;
    private EditText repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignIn=(TextView) findViewById(R.id.signin);
        nextbtn = (Button) findViewById(R.id.nextbtn);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        uname = (EditText) findViewById(R.id.uname);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);


        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAlreadySignedUp();
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGoToNextPage();
            }
        });


    }


    public void onAlreadySignedUp()
    {
        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void onGoToNextPage()
    {

        CustomerVM customerVM = new CustomerVM();



        if(
                !fname.getText().toString().equals("") &&
                !lname.getText().toString().equals("") &&
                !uname.getText().toString().equals("") &&
                !email.getText().toString().equals("") &&
                !mobile.getText().toString().equals("") &&
                !password.getText().toString().equals(""))
        {

            customerVM.setFirstName(fname.getText().toString());
            customerVM.setLastName(lname.getText().toString());
            customerVM.setUserName(uname.getText().toString());
            customerVM.setEmail(email.getText().toString());
            customerVM.setMobile(Integer.parseInt(mobile.getText().toString()));
            customerVM.setPassword(password.getText().toString());
            String pass=repassword.getText().toString();


            if(password.getText().toString().equals(pass))
            {
                Intent intent = new Intent(SignUpActivity.this,CreateHealthProfileActivity.class);
                intent.putExtra("customerVM", new Gson().toJson(customerVM));
                startActivity(intent);
            }
            else
            {
                Toast.makeText(SignUpActivity.this,"Passwords does not match", Toast.LENGTH_SHORT).show();
            }

        }
        else {

            Toast.makeText(SignUpActivity.this,"All the fields are required",Toast.LENGTH_SHORT).show();

        }




    }
}
