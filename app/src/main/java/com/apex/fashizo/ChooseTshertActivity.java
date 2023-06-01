package com.apex.fashizo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apex.fashizo.Utility.Utilities;
import com.apex.fashizo.adapter.RecyclerGarments4Adapter;
import com.apex.fashizo.adapter.RecyclerGarmentsAdapter;
import com.apex.fashizo.bean.CategoryBeans;

import java.util.ArrayList;
import java.util.TreeMap;

public class ChooseTshertActivity extends AppCompatActivity {
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerChooseGarments;
    RecyclerView recyclerChooseNeckStyle;
    RecyclerView recyclerChooseSeleevs;
    TextView nextBtn;
    LinearLayout nextBtnButton;
    public Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_tshert);
        init();
    }

    private void init(){
        Gramments();
        Collar();
        Seleevs();
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadNext();
            }
        });
        nextBtnButton = findViewById(R.id.nextBtnBottom);
        nextBtnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadNext();
            }
        });
    }

    private void LoadNext(){
        startActivity(new Intent(ChooseTshertActivity.this,ChoosePentsActivity.class));
        finish();
    }

    private void Gramments(){
        ArrayList<CategoryBeans> listOfCat = new ArrayList<>();
        listOfCat.add(Utilities.getCategoryBeans(this,"1",R.drawable.cloth_gr,"SHIRT",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"2",R.drawable.tshirt_gr,"T-SHIRT",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"3",R.drawable.pants_gr,"PANTS",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"4",R.drawable.menblazer_gr,"MEN BLAZER",false));

        RecyclerGarments4Adapter adapter=new RecyclerGarments4Adapter("garments",listOfCat,this);

        recyclerChooseGarments = findViewById(R.id.chooseGarments);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerChooseGarments.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseGarments.setAdapter(adapter);

    }


    private void Collar(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.clr_cr5,"Collar band",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.srt_clr1,"O-Collar",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.srt_clr2,"V-Collar",false));
        TreeMap<String,ArrayList<CategoryBeans>> listTreeMap = new TreeMap<>();
        listTreeMap.put("Collar",listOfCatCollar);
        Utilities.chooseGrmt.put("TSHIRT",listTreeMap);

        RecyclerGarmentsAdapter adapterCollar=new RecyclerGarmentsAdapter("collar",listOfCatCollar,this);

        recyclerChooseNeckStyle = findViewById(R.id.chooseCollar);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseNeckStyle.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseNeckStyle.setAdapter(adapterCollar);


    }

    private void Seleevs(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.str_slv1,"Full Seleeves",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.str_slv1,"Half Seleeves",false));
        TreeMap<String,ArrayList<CategoryBeans>> listTreeMap = new TreeMap<>();
        listTreeMap.put("seleeves",listOfCatCollar);
        Utilities.chooseGrmt.put("TSHIRT",listTreeMap);

        RecyclerGarmentsAdapter adapterCollar=new RecyclerGarmentsAdapter("seleevs",listOfCatCollar,this);
        recyclerChooseSeleevs = findViewById(R.id.chooseSleeves);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseSeleevs.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseSeleevs.setAdapter(adapterCollar);


    }


}