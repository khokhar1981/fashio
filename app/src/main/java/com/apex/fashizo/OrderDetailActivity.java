package com.apex.fashizo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.apex.fashizo.Utility.Utilities;
import com.apex.fashizo.bean.CategoryBeans;

import java.util.ArrayList;
import java.util.TreeMap;

public class OrderDetailActivity extends AppCompatActivity {
    LinearLayout back_btn;
    ImageView imageName;
    ImageView imageCollar;
    ImageView imageSeleeves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_order);
        init();
    }

    private void init(){
        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadActivity(new Intent(OrderDetailActivity.this,FormActivity.class));
            }
        });
        imageName = findViewById(R.id.itemName);
        imageCollar = findViewById(R.id.itemCollar);
        imageSeleeves = findViewById(R.id.itemSeleeves);
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

            }
        }
    }
    @Override
    public void onBackPressed() {
        LoadActivity(new Intent(this,FormActivity.class));
    }
    private void LoadActivity(Intent setIntent){
        startActivity(setIntent);
        finish();
    }

}