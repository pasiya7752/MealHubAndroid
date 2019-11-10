package com.example.mealhubandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealhubandroid.Models.CustomerVM;
import com.example.mealhubandroid.Services.APIUtils;
import com.example.mealhubandroid.Services.CustomerService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthAnalysisMainActivity extends AppCompatActivity {


    public static final String MY_PREFS_NAME = "MyPrefsFile";

    BarChart barChart;
    ArrayList<String> dates = new ArrayList<>();
    Random random;
    ArrayList<BarEntry> barEntries;


    TextView bmi,activityLevel;
    Button logFood,logExcercise,healthAnalysis;


    String customerId;
    CustomerService customerService;
    CustomerVM customerVM = new CustomerVM();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_analysis_main);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        customerId = prefs.getString("userId", " ");

        customerService = APIUtils.getCustomerService();
        getUserData();


        barChart = (BarChart) findViewById(R.id.bargraph);


        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.setDescription(null);
        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getXAxis().setEnabled(false);
        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);

        XAxis x = barChart.getXAxis();
        x.setAxisMinimum((float) 0.0);

        YAxis y = barChart.getAxisLeft();
        y.setAxisMinimum((float) 0.0);
        createRandomBarGraph();


        bmi = (TextView) findViewById(R.id.bmi);
        activityLevel = (TextView) findViewById(R.id.activity);

        logFood = (Button) findViewById(R.id.logFood);
        logExcercise = (Button) findViewById(R.id.logExcercise);
        healthAnalysis = (Button) findViewById(R.id.healthAnalysis);

        logFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFoodLogger();
            }
        });




    }


    public void createRandomBarGraph(){


        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();

        // fill the lists
        for(int i = 0; i < 1; i++) {
            entriesGroup1.add(new BarEntry(i, 50));
            entriesGroup2.add(new BarEntry(i, 35));
        }

        BarDataSet set1 = new BarDataSet(entriesGroup1, "Calorie Intake");
        BarDataSet set2 = new BarDataSet(entriesGroup2, "Calorie Limit");
        set1.setColor(Color.parseColor("#000000"));
        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.15f; // x2 dataset
        // (0.02 + 0.45) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData data = new BarData(set1,set2);
        data.setBarWidth(barWidth); // set the width of each bar
        barChart.setData(data);
        barChart.groupBars(0f, groupSpace, barSpace); // perform the "explicit" grouping
        barChart.invalidate(); // refresh
        XAxis xAxis = barChart.getXAxis();
        xAxis.setCenterAxisLabels(true);
    }




    public void getUserData()
    {
        Call<CustomerVM> call = customerService.findCustomerById(customerId);
        call.enqueue(new Callback<CustomerVM>() {
            @Override
            public void onResponse(Call<CustomerVM> call, Response<CustomerVM> response) {
                customerVM = response.body();
                System.out.println("@@@@@@@@@@"+customerVM.getActivityLevel());

                bmi.setText("BMI level : "+customerVM.getBmi());
                activityLevel.setText("Activity level : "+customerVM.getActivityLevel());
            }

            @Override
            public void onFailure(Call<CustomerVM> call, Throwable t) {

            }
        });
    }

    public void toFoodLogger()
    {
        Intent intent = new Intent(HealthAnalysisMainActivity.this,LogFoodActivity.class);
        startActivity(intent);
    }


}
