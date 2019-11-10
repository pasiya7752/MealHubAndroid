package com.example.mealhubandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.mealhubandroid.DTO.RestaurantMeals.RestaurantMealResponse;
import com.example.mealhubandroid.Models.RestaurantMealVM;
import com.example.mealhubandroid.Models.ServiceProviderVM;
import com.example.mealhubandroid.Services.APIUtils;
import com.example.mealhubandroid.Services.RestaurantMealService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantViewerActivity extends AppCompatActivity {


    private ServiceProviderVM serviceProviderVM;
    List<RestaurantMealVM> restaurantMealVMS = new ArrayList<>();
    RestaurantMealService restaurantMealService;
    TextView restName;
    TextView addr;
    TextView contact;
    LinearLayout top;
    Button review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_viewer);

        serviceProviderVM= new Gson().fromJson(getIntent().getSerializableExtra("serviceProvider").toString(), new TypeToken<ServiceProviderVM>(){}.getType());

        restName = (TextView) findViewById(R.id.restName);
        addr = (TextView) findViewById(R.id.addr);
        contact = (TextView) findViewById(R.id.contact);
        review = (Button) findViewById(R.id.review);

        restName.setText(serviceProviderVM.getCompanyName());
        addr.setText(serviceProviderVM.getCompanyAddress());
        contact.setText(String.valueOf(serviceProviderVM.getContactNo()));

        top = (LinearLayout) findViewById(R.id.top);


        restaurantMealService = APIUtils.getRestaurantMealService();

        getRestaurantMenuItems();

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toReviewPanel();
            }
        });

    }

    public void getRestaurantMenuItems()
    {


        Call<RestaurantMealResponse> call = restaurantMealService.getAllRestaurantMeals(serviceProviderVM.getId());

        call.enqueue(new Callback<RestaurantMealResponse>() {
            @Override
            public void onResponse(Call<RestaurantMealResponse> call, Response<RestaurantMealResponse> response) {

                restaurantMealVMS = response.body().restaurantMealVMS;
                generateMenuItems();


            }

            @Override
            public void onFailure(Call<RestaurantMealResponse> call, Throwable t) {

            }
        });
    }

    public void generateMenuItems()
    {

        LinearLayout menuScroll = new LinearLayout(this);
        menuScroll = (LinearLayout) findViewById(R.id.menuScroll);
        menuScroll.removeAllViews();
        Typeface typeface = ResourcesCompat.getFont(this, R.font.loveyalikeasis);

        for(int i=0;i<restaurantMealVMS.size();i++)
        {
                LinearLayout layout1 = new LinearLayout(this);
                layout1.setOrientation(LinearLayout.HORIZONTAL);
                layout1.setBackgroundColor(Color.parseColor("#30000000"));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.bottomMargin = 10;
                params.topMargin = 10;
                layout1.setLayoutParams(params);

                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(260,260);
                imageView.setLayoutParams(params1);
                Picasso.get().load(restaurantMealVMS.get(i).getImagePath()).into(imageView);
                layout1.addView(imageView);

                LinearLayout layout2 = new LinearLayout(this);
                layout2.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params2.gravity = Gravity.CENTER_VERTICAL;
                layout2.setLayoutParams(params2);

                TextView foodName = new TextView(this);
                LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                foodName.setTextSize(17);
                foodName.setTextColor(Color.parseColor("#FFA401"));
                foodName.setTypeface(typeface);
                foodName.setText(restaurantMealVMS.get(i).getFoodName());
                layout2.addView(foodName);


                TextView price = new TextView(this);
                LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                price.setTextSize(17);
                price.setTextColor(Color.parseColor("#000000"));
                price.setTypeface(typeface);
                price.setText("Rs."+String.valueOf(restaurantMealVMS.get(i).getPrice())+".00");
                price.setLayoutParams(params4);
                layout2.addView(price);


                Button viewMore = new Button(this);
                LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                viewMore.setText("VIEW MORE");
                viewMore.setLayoutParams(params5);
                layout2.addView(viewMore);

                layout1.addView(layout2);
                menuScroll.addView(layout1);



        }

        }

        public void toReviewPanel()
        {
            Intent intent = new Intent(RestaurantViewerActivity.this,ReviewPanelActivity.class);

            intent.putExtra("serviceProviderVM", new Gson().toJson(serviceProviderVM));

            startActivity(intent);
        }




}

