package com.apex.fashizo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.apex.fashizo.Utility.Utilities;
import com.apex.fashizo.adapter.RecyclerViewAdapter;
import com.apex.fashizo.bean.CategoryBeans;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        ArrayList<CategoryBeans> listOfCat = new ArrayList<>();
        listOfCat.add(Utilities.getCategoryBeans(this,"1",R.drawable.man_icon,"Customize Male Shirt",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"2",R.drawable.female_icon,"Customize Female Shirt",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"3",R.drawable.threed_icon,"3D Printing",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"4",R.drawable.nngravingservice,"Engraving Service",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"5",R.drawable.mag_icon,"Mug Printing",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"6",R.drawable.accessories_icon,"Tech Accessories",false));
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(listOfCat,this);

        recyclerView = (RecyclerView) findViewById(R.id.itemRecyclerView);
// set a GridLayoutManager with default vertical orientation and 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setAdapter(adapter);

    }


}