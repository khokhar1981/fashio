package com.apex.fashizo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

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
        listOfCat.add(getCategoryBeans("1","man_icon.png","Customize Male Shirt"));
        listOfCat.add(getCategoryBeans("2","female_icon.png","Customize Female Shirt"));
        listOfCat.add(getCategoryBeans("3","threed_icon.png","3D Printing"));
        listOfCat.add(getCategoryBeans("4","nngravingservice.png","Engraving Service"));
        listOfCat.add(getCategoryBeans("5","mag_icon.png","Mug Printing"));
        listOfCat.add(getCategoryBeans("6","accessories_icon.png","Tech Accessories"));
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(listOfCat,this);

        recyclerView = (RecyclerView) findViewById(R.id.itemRecyclerView);
// set a GridLayoutManager with default vertical orientation and 3 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setAdapter(adapter);

    }

    private CategoryBeans getCategoryBeans(String id,String name,String title){
        CategoryBeans beans =  null;
        try {
            beans = new CategoryBeans();
            beans.setId(id);
            beans.setName(title);
            beans.setImage(getDrawable(name));
        }catch(Exception ex){

        }
        return beans;
    }
    private Drawable getDrawable(String name){
        Drawable drawable = null;
        try{
            InputStream ims = getAssets().open(name);
            // load image as Drawable
            drawable = Drawable.createFromStream(ims, null);
            // set image to ImageView

        }catch(Exception ex){

        }
        return drawable;
    }
}