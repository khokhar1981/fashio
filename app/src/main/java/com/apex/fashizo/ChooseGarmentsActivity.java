package com.apex.fashizo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apex.fashizo.adapter.RecyclerViewAdapter;
import com.apex.fashizo.bean.CategoryBeans;

import java.io.InputStream;
import java.util.ArrayList;

public class ChooseGarmentsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garments);

    }


}