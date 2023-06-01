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

public class ChooseBlazarActivity extends AppCompatActivity {
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerChooseGarments;
    RecyclerView recyclerChooseCollar;
    RecyclerView recyclerChooseSeleevs;
    RecyclerView recyclerChooseCuff;
    RecyclerView recyclerChooseButtons;
    RecyclerView recyclerChooseLong;
    TextView nextBtn;
    LinearLayout nextBtnButton;
    public Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_menblazar);
        init();
    }

    private void init(){
        Gramments();
        Collar();
        Seleevs();
        Cuff();
        Button();
        Long();
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
        startActivity(new Intent(ChooseBlazarActivity.this,CanvasActivity.class));
        finish();
    }

    private void Gramments(){
        ArrayList<CategoryBeans> listOfCat = new ArrayList<>();
        Utilities.chooseGarmentList.put("1",false);
        Utilities.chooseGarmentList.put("2",false);
        Utilities.chooseGarmentList.put("3",false);
        Utilities.chooseGarmentList.put("4",false);
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
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.clr_cr5,"Customize Male Shirt",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.menblazer_gr,"Customize Female Shirt",false));
        RecyclerGarmentsAdapter adapterCollar=new RecyclerGarmentsAdapter("collar",listOfCatCollar,this);

        recyclerChooseCollar = findViewById(R.id.chooseNeck);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseCollar.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseCollar.setAdapter(adapterCollar);


    }

    private void Seleevs(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.menblazer_gr,"Customize Male Shirt",false));
        RecyclerGarmentsAdapter adapterCollar=new RecyclerGarmentsAdapter("seleevs",listOfCatCollar,this);

        recyclerChooseSeleevs = findViewById(R.id.chooseSleeves);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseSeleevs.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseSeleevs.setAdapter(adapterCollar);


    }

    private void Cuff(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1","cf_barrel.png","Customize Male Shirt",false));
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2","cf_dquare.png","Customize Female Shirt",false));
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3","cf_empty.png","Customize Female Shirt",false));
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"4","cf_french.png","Customize Female Shirt",false));
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"5","cf_mitered.png","Customize Female Shirt",false));
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"6","cf_rounded.png","Customize Female Shirt",false));
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"7","cf_trunback.png","Customize Female Shirt",false));
//        int count = 8;
//        for(int index=0; index<10; index++){
//            listOfCatCollar.add(Utilities.getCategoryBeans(this,""+count,"cf_trunback.png","Customize Female Shirt",false));
//            count++;
//        }
        RecyclerGarmentsAdapter adapterCollar=new RecyclerGarmentsAdapter("cuff",listOfCatCollar,this);

        recyclerChooseCuff = findViewById(R.id.chooseCuff);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseCuff.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseCuff.setAdapter(adapterCollar);


    }

    private void Button(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1","btn_metal.png","Customize Male Shirt",false));
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2","btn_plastic.png","Customize Female Shirt",false));
        RecyclerGarmentsAdapter adapterCollar=new RecyclerGarmentsAdapter("button",listOfCatCollar,this);

        recyclerChooseButtons = findViewById(R.id.chooseButtom);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseButtons.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseButtons.setAdapter(adapterCollar);


    }

    private void Long(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1","ln_longer.png","Customize Male Shirt",false));
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2","ln_normal.png","Customize Female Shirt",false));
//        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3","ln_hot.png","Customize Female Shirt",false));
        RecyclerGarmentsAdapter adapterCollar=new RecyclerGarmentsAdapter("long",listOfCatCollar,this);

        recyclerChooseLong = findViewById(R.id.chooseLong);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseLong.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseLong.setAdapter(adapterCollar);


    }

}