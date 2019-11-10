package com.example.mealhubandroid;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.mealhubandroid.DTO.Review.ReviewRequest;
import com.example.mealhubandroid.DTO.Review.ReviewResponse;
import com.example.mealhubandroid.Models.ReviewVM;
import com.example.mealhubandroid.Models.ServiceProviderVM;
import com.example.mealhubandroid.Services.APIUtils;
import com.example.mealhubandroid.Services.ReviewService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewPanelActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private ServiceProviderVM serviceProviderVM;
    ReviewService reviewService;
    TextView restName;
    TextView addr;
    TextView contact;
    Button addReview;
    RatingBar ratingBar;
    EditText review;
    ReviewVM reviewVM = new ReviewVM() ;
    List<ReviewVM> reviewVMS = new ArrayList<>();
    String customerId;
    String customerName;
    ReviewRequest reviewRequest = new ReviewRequest();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_panel);

        serviceProviderVM= new Gson().fromJson(getIntent().getSerializableExtra("serviceProviderVM").toString(), new TypeToken<ServiceProviderVM>(){}.getType());
        restName = (TextView) findViewById(R.id.restName);
        addr = (TextView) findViewById(R.id.addr);
        contact = (TextView) findViewById(R.id.contact);
        addReview = (Button) findViewById(R.id.addReview);

        restName.setText(serviceProviderVM.getCompanyName());
        addr.setText(serviceProviderVM.getCompanyAddress());
        contact.setText(String.valueOf(serviceProviderVM.getContactNo()));

        ratingBar =(RatingBar) findViewById(R.id.healthRate);
        review = (EditText) findViewById(R.id.review);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        customerId = prefs.getString("userId", " ");//"No name defined" is the default value.
        customerName = prefs.getString("username", " ");
        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
            }
        });
        reviewService = APIUtils.getReviewService();


        getAllReviews();
    }

    public void addReview()
    {
        reviewVM.setRestaurantId(serviceProviderVM.getId());
        reviewVM.setReview(review.getText().toString());
        reviewVM.setHealthRate(ratingBar.getRating());
        reviewVM.setCustomerId(customerId);
        reviewVM.setCustomerName(customerName);

        reviewRequest.reviewVM=reviewVM;

        Call<ReviewResponse> call = reviewService.addReview(reviewRequest);
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                Toast.makeText(ReviewPanelActivity.this, "Review added successfully", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });
    }

    public void getAllReviews()
    {
        Call<ReviewResponse> call = reviewService.getAllReviews(serviceProviderVM.getId());
        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                reviewVMS = response.body().reviewVMS;

                generateReviewElements();
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {

            }
        });
    }

    public void generateReviewElements()
    {
        LinearLayout viewReviewsPanel = new LinearLayout(this);

        viewReviewsPanel = (LinearLayout) findViewById(R.id.viewReviewsPanel);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.loveyalikeasis);
        for(int i=0;i<reviewVMS.size();i++)
        {
            LinearLayout layout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setBackgroundResource(R.drawable.reviewback);
            layout.setLayoutParams(params);

            TextView username = new TextView(this);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.leftMargin=10;
            username.setText(reviewVMS.get(i).getCustomerName());
            username.setTypeface(username.getTypeface(), Typeface.BOLD);
            username.setTextSize(20);
            username.setTypeface(typeface);
            username.setTextColor(Color.parseColor("#FF9800"));
            username.setLayoutParams(params1);
            layout.addView(username);

            TextView review = new TextView(this);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params2.leftMargin=10;
            review.setText(reviewVMS.get(i).getReview());
            review.setTextSize(18);
            review.setTypeface(username.getTypeface(), Typeface.ITALIC);
            review.setTextColor(Color.parseColor("#85222222"));
            review.setLayoutParams(params2);
            layout.addView(review);

            RatingBar healthRate = new RatingBar(this,null,R.attr.ratingBarStyleIndicator);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params3.leftMargin=-70;
            healthRate.setNumStars(5);
            healthRate.setScaleX((float) 0.5);
            healthRate.setScaleY((float) 0.5);
            healthRate.setRating(reviewVMS.get(i).getHealthRate());
            healthRate.setLayoutParams(params3);
            layout.addView(healthRate);


            viewReviewsPanel.addView(layout);

        }
    }
}
