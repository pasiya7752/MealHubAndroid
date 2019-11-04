package com.example.mealhubandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Continue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Continue=(Button)findViewById(R.id.Continue);

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
