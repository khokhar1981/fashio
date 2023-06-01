package com.apex.fashizo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerDetailFormActivity extends AppCompatActivity {
    LinearLayout next_btn;
    LinearLayout back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_customerdetailform);
        init();
    }

    private void init(){
        next_btn = findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadActivity(new Intent(CustomerDetailFormActivity.this,FormActivity.class));
            }
        });

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadActivity(new Intent(CustomerDetailFormActivity.this,CanvasActivity.class));
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