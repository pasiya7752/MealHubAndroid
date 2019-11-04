package com.example.mealhubandroid;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.*;

import com.example.mealhubandroid.Models.MealVM;
import com.example.mealhubandroid.Services.MealApiService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CreateMealActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {

    private static final String TAG = CreateMealActivity.class.getSimpleName();
    private ImageView imageView;
    private static final String IMAGE_VIEW_TAG = "LAUNCHER LOGO";
    private ArrayList<MealVM> mealVMS;
    private ArrayList<MealVM> selectedMealVMS;
    private BigDecimal calories= new BigDecimal(278);
    private BigDecimal carbs= new BigDecimal(45);
    private BigDecimal fats= new BigDecimal(4.5);
    private BigDecimal proteins= new BigDecimal(5.5);
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

        TextView calorie = findViewById(R.id.calories);
        TextView carb = findViewById(R.id.carbs);
        TextView fat = findViewById(R.id.fat);
        TextView protein = findViewById(R.id.protein);

        calorie.setText("Approximate Total Calories = "+calories.toString());
        carb.setText("Approximate Total Carbs = "+carbs.toString());
        fat.setText("Approximate Total Fat = "+fats.toString());
        protein.setText("Approximate Total Protein = "+proteins.toString());



    }

    public void generateDraggedMeals(String id)
    {
        for (int i=0;i<mealVMS.size();i++) {
            if(mealVMS.get(i).getId()==id)
            {
                selectedMealVMS.add(mealVMS.get(i));
                break;
            }
        }
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
                        findViews();
                        implementEvents();
                    }
//                    mealVMS= response.getJSONArray("mealVMS");
                    System.out.println("##############################################"+mealVMS.get(0).getTotalCalories());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                // Pull out the first event on the public timeline
//
//                // Do something with the response
//                System.out.println(response.get);
//            }
        });

    }

    //Find all views and set Tag to all draggable views
    private void findViews() {
        for(int i=0;i<mealVMS.size();i++)
        {
            IMGS[i].setOnLongClickListener(this);
            IMGS[i].setTag(mealVMS.get(i).getId());
        }
    }


    //Implement long click and drag listener
    private void implementEvents() {
        //add or remove any view that you don't want to be dragged

        for(int i=0;i<mealVMS.size();i++)
        {
            IMGS[i].setOnLongClickListener(this);

        }


        //add or remove any layout view that you don't want to accept dragged view
//        findViewById(R.id.top_layout).setOnDragListener(this);
        findViewById(R.id.left_layout).setOnDragListener(this);
    }

    private void generateImageViews()
    {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$"+mealVMS.get(0).getImagePath().replace("\\","/"));
        LinearLayout layout = (LinearLayout)findViewById(R.id.top_layout);
        layout.removeAllViews();
        for(int i=0;i<mealVMS.size();i++)
        {

            CircularImageView image = new CircularImageView(this);
            image.setLayoutParams(new android.view.ViewGroup.LayoutParams(200,200));
            image.setMaxHeight(100);
//            image.setShadowRadius(50);
            image.setMaxWidth(100);
            Picasso.get().load("https://www.islandsmile.org/wp-content/uploads/2018/01/SRI-LANKAN-DEVILLED-CHICKEN-STIR-FRY.-islandsmile.org-4499.jpg").into(image);

            image.setId(i);
            // Adds the view to the layout
            layout.addView(image);

            IMGS[i] = image;
        }
    }

    @Override
    public boolean onLongClick(View view) {
        // Create a new ClipData.
        // This is done in two steps to provide clarity. The convenience method
        // ClipData.newPlainText() can create a plain text ClipData in one step.

        // Create a new ClipData.Item from the ImageView object's tag
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

        ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

        // Instantiates the drag shadow builder.
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        // Starts the drag
        view.startDrag(data//data to be dragged
                , shadowBuilder //drag shadow
                , view//local data about the drag and drop operation
                , 0//no needed flags
        );

        //Set view visibility to INVISIBLE as we are going to drag the view
        view.setVisibility(View.INVISIBLE);
        return true;
    }

    // This is the method that the system calls when it dispatches a drag event to the
    // listener.

    @Override
    public boolean onDrag(View view, DragEvent event) {
        // Defines a variable to store the action type for the incoming event
        int action = event.getAction();
        // Handles each of the expected events
        System.out.println("^^^^^^^^^^^^^^^^^^"+event);

//        if (event!=null) {
//            String id = event.getClipDescription().getLabel().toString();
//            count++;
//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^"+event.getClipDescription().getLabel().toString());
//        }
//        if(event.getResult()&&count==0)
//        {
////            generateDraggedMeals(event);
//            count++;
//        }

        boolean isSuccess=false;

        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept
                    // data.

                    //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);//set background color to your view

                    // Invalidate the view to force a redraw in the new tint
                    //  view.invalidate();

                    // returns true to indicate that the View can accept the dragged data.
                    isSuccess = true;
                    return isSuccess;

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                isSuccess=true;
                return isSuccess;

            case DragEvent.ACTION_DRAG_ENTERED:
                // Applies a YELLOW or any color tint to the View, when the dragged view entered into drag acceptable view
                // Return true; the return value is ignored.

//                view.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

                // Invalidate the view to force a redraw in the new tint
                view.invalidate();
                isSuccess=true;
                return isSuccess;
            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event
                isSuccess=true;
                return isSuccess;
            case DragEvent.ACTION_DRAG_EXITED:
                // Re-sets the color tint to blue, if you had set the BLUE color or any color in ACTION_DRAG_STARTED. Returns true; the return value is ignored.

                //  view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);

                //If u had not provided any color in ACTION_DRAG_STARTED then clear color filter.
                view.getBackground().clearColorFilter();
                // Invalidate the view to force a redraw in the new tint
                view.invalidate();

                isSuccess=true;
                return isSuccess;
            case DragEvent.ACTION_DROP:



                // Gets the item containing the dragged data
                ClipData.Item item = event.getClipData().getItemAt(0);
                System.out.println("11111111111111111111111111111111111111111111111111111111111111111111111"+DragEvent.ACTION_DRAG_LOCATION);

                // Gets the text data from the item.
                String dragData = item.getText().toString();

                // Displays a message containing the dragged data.
                Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

                // Turns off any color tints
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                View v = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) v.getParent();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+v.getId());
                generateImageViews();
                findViews();
                implementEvents();
                LinearLayout ll = findViewById(R.id.left_layout);
                System.out.println("@@@@@@@@@@@@########"+ll.getChildCount());
                owner.removeView(v);//remove the dragged view
                LinearLayout container = (LinearLayout) view;//caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                container.addView(v);//Add the dragged view
                v.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE

                // Returns true. DragEvent.getResult() will return true.
                isSuccess = true;
                return isSuccess;
            case DragEvent.ACTION_DRAG_ENDED:
                // Turns off any color tinting
                view.getBackground().clearColorFilter();

                // Invalidates the view to force a redraw
                view.invalidate();

                // Does a getResult(), and displays what happened.
//                if (event.getResult())
//                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
//
//                else
//                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();


                // returns true; the value is ignored.
                isSuccess=true;
                return isSuccess;

            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }

        if (!isSuccess)
        {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!! Isnt Successfull");
        }

        return isSuccess;
    }


}