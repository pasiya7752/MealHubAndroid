package com.example.mealhubandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealhubandroid.Models.MealVM;
import com.example.mealhubandroid.Services.MealApiService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CreateMealActivity extends AppCompatActivity  {

    private static final String TAG = CreateMealActivity.class.getSimpleName();
    private ImageView imageView;
    private static final String IMAGE_VIEW_TAG = "LAUNCHER LOGO";
    private ArrayList<MealVM> mealVMS;
    private ArrayList<MealVM> selectedMealVMS= new ArrayList<>();
    private BigDecimal calories= new BigDecimal(0);
    private BigDecimal carbs= new BigDecimal(0);
    private BigDecimal fats= new BigDecimal(0);
    private BigDecimal proteins= new BigDecimal(0);

    TextView calorie;
    TextView carb;
    TextView fat;
    TextView protein;


    int mealCount=0;
    int count=0;
    private ImageView[] IMGS = new ImageView[10];
//    private String[] imageViewIdArray= new String[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);



        try {
            onGet();

        } catch (Exception e) {
            e.printStackTrace();
        }


        calorie =(TextView) findViewById(R.id.calories);
        carb =(TextView) findViewById(R.id.carbs);
        fat =(TextView) findViewById(R.id.fat);
        protein =(TextView) findViewById(R.id.protein);

        LinearLayout plate = findViewById(R.id.left_layout);

        calorie.setText("Approximate Total Calories = "+calories.toString());
        carb.setText("Approximate Total Carbs = "+carbs.toString());
        fat.setText("Approximate Total Fat = "+fats.toString());
        protein.setText("Approximate Total Protein = "+proteins.toString());

        this.fadeInAnimation(calorie,1500);
        this.fadeInAnimation(carb,1500);
        this.fadeInAnimation(fat,1500);
        this.fadeInAnimation(protein,1500);
        this.fadeInAnimation(plate,2000);





    }


    public void onGet()  throws JSONException {

        MealApiService.getAll("getAllMeals", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    System.out.println(response.getJSONArray("mealVMS").length());

                    if (response.getJSONArray("mealVMS") != null) {
                        mealVMS= new Gson().fromJson(response.getJSONArray("mealVMS").toString(), new TypeToken<List<MealVM>>(){}.getType());
                        generateImageViews();
                    }
//                    mealVMS= response.getJSONArray("mealVMS");
                    System.out.println("##############################################"+mealVMS.get(0).getTotalCalories());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

    }




    private void generateImageViews()
    {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$"+mealVMS.get(0).getImagePath().replace("\\","/"));
        LinearLayout layout = (LinearLayout)findViewById(R.id.top_layout);


        layout.removeAllViews();
        for(int i=0;i<mealVMS.size();i++)
        {
            final int j = i;


            LinearLayout layout1 = new LinearLayout(this);
            LinearLayout.LayoutParams params0=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params0.gravity=Gravity.CENTER;
            layout1.setOrientation(LinearLayout.VERTICAL);
            layout1.setLayoutParams(params0);

            TextView foodName = new TextView(this);
            LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT);
            params3.gravity = Gravity.CENTER;
            foodName.setLayoutParams(params3);



            CircularImageView image = new CircularImageView(this);
            LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(200,200);
            params2.gravity = Gravity.CENTER;
            image.setLayoutParams(params2);
//            image.setMaxHeight(100);
//            image.setMaxWidth(100);
            Picasso.get().load(mealVMS.get(i).getImagePath()).into(image);

            image.setId(i);

            foodName.setText(mealVMS.get(i).getFoodName());
            layout1.addView(image);
            layout1.addView(foodName);



            layout.addView(layout1);

            IMGS[i] = image;

            this.fadeInAnimation(image,1500);

            if(IMGS[i]!=null)
            {
                IMGS[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        selectFoods(mealVMS.get(j));

//                        System.out.println(mealVMS.get(j).getFoodName());
                    }
                });
            }



        }
    }



    public static void fadeInAnimation(final View view, long animationDuration) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(animationDuration);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        view.startAnimation(fadeIn);
    }
    public void selectFoods(MealVM mealVM)
    {

        System.out.println(mealVM.getFoodName());
        selectedMealVMS.add(mealVM);
        processSelectedFoodItems();
        calculateCalories();
    }

    public void removeFoods(int j)
    {

        selectedMealVMS.remove(j);
        processSelectedFoodItems();
        calculateCalories();
    }
    public void processSelectedFoodItems()
    {

        LinearLayout parent = (LinearLayout) findViewById(R.id.plateInside);

        parent.removeAllViews();
        for(int i=0;i<selectedMealVMS.size();i++)
        {

            final int j = i;
            LinearLayout layout1 = new LinearLayout(this);
            LinearLayout.LayoutParams params0=new LinearLayout.LayoutParams(500, ViewGroup.LayoutParams.WRAP_CONTENT);
            params0.gravity = Gravity.CENTER_VERTICAL;
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.setLayoutParams(params0);

            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(80,80);
            params1.gravity = Gravity.CENTER_VERTICAL;
            imageView.setLayoutParams(params1);
            imageView.setBackgroundResource(R.drawable.bullet);
            layout1.addView(imageView);

            TextView foodname = new TextView(this);
            LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params2.gravity = Gravity.CENTER_VERTICAL;
            foodname.setLayoutParams(params2);
            foodname.setText(selectedMealVMS.get(i).getFoodName());
            layout1.addView(foodname);

            Button rmbtn = new Button(this);
            LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(50, 50);
            params3.gravity = Gravity.CENTER_VERTICAL;
            rmbtn.setBackgroundColor(Color.RED);
            rmbtn.setBackgroundResource(R.drawable.tags_rounded_corners);
            rmbtn.setText("X");
            rmbtn.setTextColor(Color.RED);
            rmbtn.setTextSize(14);
            rmbtn.setPadding(0,0,0,0);
            rmbtn.setLayoutParams(params3);

            rmbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeFoods(j);
                }
            });


            layout1.addView(rmbtn);

            parent.addView(layout1);
        }


    }

    public void calculateCalories()
    {
        calories= new BigDecimal(0);
        carbs= new BigDecimal(0);
        fats= new BigDecimal(0);
        proteins= new BigDecimal(0);

        for(int i=0;i<selectedMealVMS.size();i++)
        {
            calories = calories.add(selectedMealVMS.get(i).getTotalCalories());
            carbs = carbs.add(selectedMealVMS.get(i).getTotalCarbs());
            fats = fats.add(selectedMealVMS.get(i).getTotalFat());
            proteins = proteins.add(selectedMealVMS.get(i).getTotalProtein());
        }

        calorie.setText("Approximate Total Calories = "+calories.toString());
        carb.setText("Approximate Total Carbs = "+carbs.toString());
        fat.setText("Approximate Total Fat = "+fats.toString());
        protein.setText("Approximate Total Protein = "+proteins.toString());
    }



}