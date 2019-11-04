package com.example.mealhubandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.mealhubandroid.Models.NutritionDataVM;
import com.example.mealhubandroid.Models.ServiceProviderVM;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GeneratedFoodNutritionResultsActivity extends AppCompatActivity {

    List<NutritionDataVM> nutritionDataVMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_food_nutrition_results);



        nutritionDataVMS= new Gson().fromJson(getIntent().getSerializableExtra("NutritionVMs").toString(), new TypeToken<List<NutritionDataVM>>(){}.getType());
        generateFoodList();
    }




    public void generateFoodList()
    {

        LinearLayout parentLayout = (LinearLayout)findViewById(R.id.parent);
        parentLayout.removeAllViews();

        for(int i=0;i<nutritionDataVMS.size();i++)
        {
            System.out.println("##############"+i+"  "+nutritionDataVMS.get(i).getFoodName());

            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params0=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundResource(R.drawable.border);
            layout.setPadding(12,12,12,12);
            layout.setLayoutParams(params0);

            TextView foodName = new TextView(this);
            foodName.setLayoutParams(new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,1f));

            foodName.setTextSize(25);
            foodName.setText(nutritionDataVMS.get(i).getFoodName());

            layout.addView(foodName);



            LinearLayout layout1 = new LinearLayout(this);
            LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.setLayoutParams(params1);


            TextView kcal = new TextView(this);
            kcal.setLayoutParams(new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,0.33f));

            kcal.setTextSize(18);
            kcal.setText("Calories: "+nutritionDataVMS.get(i).getCalories().toString()+nutritionDataVMS.get(i).getMeasurement());

            layout1.addView(kcal);

            TextView fat = new TextView(this);
            fat.setLayoutParams(new TableLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,0.33f));

            fat.setTextSize(18);
            fat.setText("Fat: "+nutritionDataVMS.get(i).getFat().toString()+nutritionDataVMS.get(i).getMeasurement());

            layout1.addView(fat);

            TextView protein = new TextView(this);
            protein.setLayoutParams(new TableLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,0.33f));

            protein.setTextSize(18);
            protein.setText("Protein: "+nutritionDataVMS.get(i).getProtein().toString()+nutritionDataVMS.get(i).getMeasurement());

            layout1.addView(protein);

            layout.addView(layout1);
            parentLayout.addView(layout);
        }

    }

}
