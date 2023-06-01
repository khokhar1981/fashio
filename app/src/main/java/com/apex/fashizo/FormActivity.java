package com.apex.fashizo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apex.fashizo.Utility.Utilities;
import com.apex.fashizo.adapter.RecyclerChildAdapter;
import com.apex.fashizo.adapter.RecyclerGarmentsAdapter;
import com.apex.fashizo.bean.CategoryBeans;

import java.util.ArrayList;
import java.util.TreeMap;

public class FormActivity extends AppCompatActivity {
    LinearLayout confirmation_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_form);
        init();
    }

    private void init(){
        confirmation_btn = findViewById(R.id.confirmation_btn);
        confirmation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadActivity(new Intent(FormActivity.this,OrderDetailActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        LoadActivity(new Intent(this,CanvasActivity.class));
    }
    private void LoadActivity(Intent setIntent){
        startActivity(setIntent);
        finish();
    }

}