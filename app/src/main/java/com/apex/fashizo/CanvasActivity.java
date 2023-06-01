package com.apex.fashizo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apex.fashizo.Utility.Utilities;
import com.apex.fashizo.adapter.RecyclerChildAdapter;
import com.apex.fashizo.adapter.RecyclerGarmentsAdapter;
import com.apex.fashizo.adapter.RecyclerMenuAdapter;
import com.apex.fashizo.adapter.RecyclerSubMenuAdapter;
import com.apex.fashizo.bean.CategoryBeans;

import java.util.ArrayList;
import java.util.TreeMap;

public class CanvasActivity extends AppCompatActivity {
    public Activity activity;
    LinearLayout menuIcon;
    LinearLayout submitIcon;
    ImageView imageName;
    ImageView imageCollar;
    ImageView imageSeleeves;
    ImageView[] imageViews;
    FrameLayout frameLayout;
    RecyclerView recyclerParentMenu;
    RecyclerView recyclerSubMenu;
    Point p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_canvas);
        menuIcon = findViewById(R.id.menu_icon);
        submitIcon = findViewById(R.id.submit_icon);
        imageName = findViewById(R.id.itemName);
        imageCollar = findViewById(R.id.itemCollar);
        imageSeleeves = findViewById(R.id.itemSeleeves);
        frameLayout = findViewById(R.id.parentFrame);
        CategoryBeans beans = Utilities.LastSelectedBeans;
        if(Utilities.LastSelectedItem.equalsIgnoreCase("shirt")){
            if(beans!=null) {
                int images = beans.getImages();
                TreeMap<String, ArrayList<CategoryBeans>> bList = Utilities.chooseGrmt.get(Utilities.LastSelectedItem);
                for(String key:bList.keySet()){
                    ArrayList<CategoryBeans> bcList = bList.get(key);
                    if(key.equalsIgnoreCase("shirts")){
                        imageName.setImageDrawable(getResources().getDrawable(Utilities.getSelectedImage(bcList)));

                    }else if(key.equalsIgnoreCase("seleevs")){
        imageSeleeves.setImageDrawable(getResources().getDrawable(Utilities.getSelectedImage(bcList)));

    }else if(key.equalsIgnoreCase("collar")){
        imageCollar.setImageDrawable(getResources().getDrawable(Utilities.getSelectedImage(bcList)));

    }
                }
                imageViews = new ImageView[bList.size()];
                int cIndex=0;
                System.out.println(Utilities.chooseGrmt);
            }

        }else if(Utilities.LastSelectedItem.equalsIgnoreCase("tshirt")) {
            if (beans != null) {
                int images = beans.getImages();
                imageName.setImageDrawable(getResources().getDrawable(images));
                TreeMap<String, ArrayList<CategoryBeans>> bList = Utilities.chooseGrmt.get(Utilities.LastSelectedItem);
                for (String key : bList.keySet()) {
                    ArrayList<CategoryBeans> bcList = bList.get(key);
                    if (key.equalsIgnoreCase("tshirts")) {
                        imageName.setImageDrawable(getResources().getDrawable(Utilities.getSelectedImage(bcList)));

                    } else if (key.equalsIgnoreCase("neck")) {
                        imageCollar.setImageDrawable(getResources().getDrawable(Utilities.getSelectedImage(bcList)));

                    }else if (key.equalsIgnoreCase("tshirt_seleevs")) {
                        imageSeleeves.setImageDrawable(getResources().getDrawable(Utilities.getSelectedImage(bcList)));

                    }
                }
                imageViews = new ImageView[bList.size()];

            }
        }
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup();
            }
        });

        submitIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitForm();
            }
        });
        init();
    }

    private void SubmitForm(){
        Intent setIntent = new Intent(this,CustomerDetailFormActivity.class);
        startActivity(setIntent);
    }

    public ImageView getImageName(int image){
        ImageView imageView =new ImageView(this);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(145,145));
        imageView.setImageDrawable(getResources().getDrawable(image));
        return imageView;
    }

    private void init(){

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        int[] location = new int[2];

        // Get the x, y location and store it in the location[] array
        // location[0] = x, location[1] = y.
        menuIcon.getLocationOnScreen(location);

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = location[0];
        p.y = location[1]+45;
        Toast.makeText(this, "Message ("+p.x+") = ("+p.y+")", Toast.LENGTH_SHORT).show();
    }

    // The method that displays the popup.
    private void showPopup() {

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout)findViewById(R.id.layout);
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_menuwindow, viewGroup);
        recyclerParentMenu = layout.findViewById(R.id.chooseLeft);
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.clr_cr5,"Collar",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.clr_cr1,"Seleeves",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.clr_cr2,"TShirt",false));
        RecyclerMenuAdapter adapterCollar=new RecyclerMenuAdapter("tshirt",listOfCatCollar,this);


        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerParentMenu.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerParentMenu.setAdapter(adapterCollar);

        recyclerSubMenu = layout.findViewById(R.id.chooseSub);
        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(this);
        popup.setContentView(layout);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x, p.y + OFFSET_Y);

        // Getting a reference to Close button, and close the popup when clicked.
//        Button close = (Button) layout.findViewById(R.id.close);
//        close.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                popup.dismiss();
//            }
//        });
    }

    public void ChangeSubMenu(int id){

        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        RecyclerSubMenuAdapter adapterCollar = null;
        if(id==1) {
            if(Utilities.LastSelectedItem.equalsIgnoreCase("shirt")){
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.clr_cr5,"Collar 1",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.clr_cr1,"Collar 2",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.clr_cr2,"Collar 3",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"4",R.drawable.clr_cr3,"Collar 4",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"5",R.drawable.clr_cr4,"Collar 5",false));

            }else if(Utilities.LastSelectedItem.equalsIgnoreCase("tshirt")){
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.collar1,"Collar 1",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.collar2,"Collar 2",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.collar3,"Collar 3",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"4",R.drawable.collar4,"Collar 4",false));

            }

            adapterCollar = new RecyclerSubMenuAdapter("collar", listOfCatCollar, this);
        }else if(id==2) {
            if(Utilities.LastSelectedItem.equalsIgnoreCase("shirt")){
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.shirt_fullseleeves,"Shirt Full Seleevs",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.shirt_halfseleeves,"Shirt Half Seleeves",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.shirt_smallseleeves,"Shirt Small Seleeves",false));

            }else if(Utilities.LastSelectedItem.equalsIgnoreCase("tshirt")){
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.fullseleeves,"Full Seleevs",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.half_seleeves,"Half Seleeves",false));

            }
            adapterCollar = new RecyclerSubMenuAdapter("seleevs", listOfCatCollar, this);
        }else if(id==3) {

            if(Utilities.LastSelectedItem.equalsIgnoreCase("shirt")){
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.shirt1,"Shirt 1",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.shirt2,"Shirt 2",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.shirt3,"Shirt 3",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"4",R.drawable.shirt4,"Shirt 4",false));

                adapterCollar = new RecyclerSubMenuAdapter("shirts", listOfCatCollar, this);

            }else if(Utilities.LastSelectedItem.equalsIgnoreCase("tshirt")){
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.tshirt,"TShirt 1",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.tshirt2,"TShirt 2",false));
                listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.tshirt3,"TShirt 3",false));

                adapterCollar = new RecyclerSubMenuAdapter("tshirts", listOfCatCollar, this);

            }

        }else if(id==4) {
            listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.btn_metal,"Metal",false));
            listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.btn_plastic,"Plastic",false));

            adapterCollar = new RecyclerSubMenuAdapter("button", listOfCatCollar, this);
        }else if(id==5){
            listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.ln_longer,"Longer",false));
            listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.ln_normal,"Normal",false));
            listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.ln_hot,"Hot",false));
            adapterCollar = new RecyclerSubMenuAdapter("long", listOfCatCollar, this);

        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerSubMenu.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerSubMenu.setAdapter(adapterCollar);

    }

    public void ChangeSubMenuSelected(String subType,int image){
        if(Utilities.LastSelectedItem.equalsIgnoreCase("tshirt")){
            if(subType.equalsIgnoreCase("seleevs")){
                imageSeleeves.setImageDrawable(getResources().getDrawable(image));
            }else if(subType.equalsIgnoreCase("collar")){
                imageCollar.setImageDrawable(getResources().getDrawable(image));
            } else if(subType.equalsIgnoreCase("tshirts")){
                imageName.setImageDrawable(getResources().getDrawable(image));
            }
        }else if(Utilities.LastSelectedItem.equalsIgnoreCase("shirt")){
            if(subType.equalsIgnoreCase("seleevs")){
                imageSeleeves.setImageDrawable(getResources().getDrawable(image));
            }else if(subType.equalsIgnoreCase("collar")){
                imageCollar.setImageDrawable(getResources().getDrawable(image));
            } else if(subType.equalsIgnoreCase("shirts")){
                imageName.setImageDrawable(getResources().getDrawable(image));
            }
        }

    }

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this,ChooseGarmentsActivity.class);
        startActivity(setIntent);
    }


}