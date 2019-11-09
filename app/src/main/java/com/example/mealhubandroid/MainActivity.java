package com.example.mealhubandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Continue;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Continue=(Button)findViewById(R.id.Continue);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn",false);//"No name defined" is the default value.

        System.out.println("ELELEELELELELE"+isLoggedIn);
        if(isLoggedIn)
        {
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        Continue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onContinue();
            }
        });
    }


    public void onContinue()
    {
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);

    }
}
