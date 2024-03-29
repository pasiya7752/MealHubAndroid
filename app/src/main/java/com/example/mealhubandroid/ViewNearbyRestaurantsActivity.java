package com.example.mealhubandroid;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealhubandroid.Models.ServiceProviderVM;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewNearbyRestaurantsActivity extends AppCompatActivity {
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;
    private double lat;
    private double lng;

    HomeActivity homeActivity = new HomeActivity();

    private List<ServiceProviderVM> serviceProviderVMS;

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nearby_restaurants);

        serviceProviderVMS= new Gson().fromJson(getIntent().getSerializableExtra("MyClass").toString(), new TypeToken<List<ServiceProviderVM>>(){}.getType());
//        getIntent().getSerializableExtra("MyClass");


        generateRestaurantViews();

    }

    private void generateRestaurantViews()
    {

        LinearLayout parentLayout = (LinearLayout)findViewById(R.id.parentLayout);
        parentLayout.removeAllViews();
        for(int i=0;i<serviceProviderVMS.size();i++)
        {

            final int j = i;

            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params0=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 130);
            params0.gravity=Gravity.LEFT;
            params0.setMargins(0, 20, 20, 0);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setLayoutParams(params0);

            ImageView image = new ImageView(this);

            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(69, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity=Gravity.LEFT;
            layoutParams.weight=1f;
            image.setLayoutParams(layoutParams);
            Picasso.get().load("https://icons-for-free.com/iconfiles/png/512/restaurant-131979029122603130.png").into(image);

            LinearLayout layout1 = new LinearLayout(this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(20, 0, 0, 0);
            layout1.setOrientation(LinearLayout.VERTICAL);
            layout1.setLayoutParams(params);

            TextView restaurantName = new TextView(this);
            restaurantName.setLayoutParams(new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    70));

            restaurantName.setTextSize(25);
            restaurantName.setText(serviceProviderVMS.get(i).getCompanyName());

            LinearLayout layout3 = new LinearLayout(this);
            LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,118);
            params1.gravity=Gravity.LEFT;
            layout3.setOrientation(LinearLayout.HORIZONTAL);
            layout3.setLayoutParams(params1);

            Button dist = new Button(this);
            LinearLayout.LayoutParams params2=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 70);
            dist.setLayoutParams(params2);
            dist.setTextColor(Color.parseColor("red"));
            dist.setTextSize(12);
            dist.setText("Go in");
            dist.setTypeface(Typeface.create("@font/loveyalikeasis", Typeface.NORMAL));

            dist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewNearbyRestaurantsActivity.this,RestaurantViewerActivity.class);

                    intent.putExtra("serviceProvider", new Gson().toJson(serviceProviderVMS.get(j)));

                    startActivity(intent);
                }
            });

            TextView HS = new TextView(this);
            LinearLayout.LayoutParams params3=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 70);
            params2.setMargins(20, 0, 0, 0);
            HS.setLayoutParams(params3);
            HS.setTextSize(25);
            HS.setText("HS:6.78");

            layout.addView(image);
            layout1.addView(restaurantName,0);
            layout3.addView(dist,0);
            layout3.addView(HS,0);
            layout1.addView(layout3);
            layout.addView(layout1);
            parentLayout.addView(layout);

        }
    }
}
