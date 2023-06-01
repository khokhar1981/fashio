package com.apex.fashizo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.apex.fashizo.adapter.RecyclerGarments4Adapter;
import com.apex.fashizo.adapter.RecyclerGarmentsAdapter;
import com.apex.fashizo.adapter.RecyclerViewAdapter;
import com.apex.fashizo.bean.CategoryBeans;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class ChooseGarmentsActivity extends AppCompatActivity {
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerChooseGarments;
    RecyclerView recyclerChooseCollar;
    RecyclerView recyclerChooseSeleevs;
    RecyclerView recyclerChooseShirts;
    RecyclerView recyclerChooseCuff;
    RecyclerView recyclerChooseButtons;
    RecyclerView recyclerChooseLong;

    RecyclerView recyclerChooseTShirtNeckStyle;
    RecyclerView recyclerChooseTShirtSleeves;
    RecyclerView recyclerChooseTShirt;

    RecyclerView recyclerChooseWaistLine;
    RecyclerView recyclerChooseWaistLong;
    RecyclerView recyclerChooseBackPocket;

    TextView nextBtn;
    LinearLayout nextBtnButton;
    LinearLayout nextBtnTshirtButton;
    LinearLayout nextBtnPentsButton;
    LinearLayout nextBtnMenBlazerButton;
    LinearLayout shirtLay;
    LinearLayout tShirtLay;
    LinearLayout pentsLay;
    LinearLayout menBlazerLay;
    public Activity activity;
    private static ChooseGarmentsActivity chooseGarmentsActivity;
    public static ChooseGarmentsActivity getChooseGarmentsActivity(){
        return chooseGarmentsActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_garments);
        chooseGarmentsActivity = this;
        init();
    }

    private void init(){
        shirtLay = findViewById(R.id.shirt);
        tShirtLay = findViewById(R.id.tshirt);
        pentsLay = findViewById(R.id.pents);
        menBlazerLay = findViewById(R.id.menBlazer);
        Gramments();
        changeLayoutStatus("shirt");

    }

    public void changeLayoutStatus(String type){
        shirtLay.setVisibility(View.GONE);
        tShirtLay.setVisibility(View.GONE);
        pentsLay.setVisibility(View.GONE);
        menBlazerLay.setVisibility(View.GONE);
        if(type.equalsIgnoreCase("shirt")){
            shirtLay.setVisibility(View.VISIBLE);
            LoadShirt();
        }else if(type.equalsIgnoreCase("tshirt")){
            tShirtLay.setVisibility(View.VISIBLE);
            LoadTShirt();
        }else if(type.equalsIgnoreCase("pents")){
            pentsLay.setVisibility(View.VISIBLE);
        }else if(type.equalsIgnoreCase("menblazer")){
            menBlazerLay.setVisibility(View.VISIBLE);
        }

    }
    private void LoadShirt(){
        listTreeMapShirt.clear();
        Collar();
        Seleevs();
        Shirt();
//        Cuff();
//        Button();
//        Long();
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

    private void LoadTShirt(){
        listTreeMapShirt.clear();
        NeckStyle();
        SleevesTShirt();
        shirtTShirt();
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadNext();
            }
        });
        nextBtnTshirtButton = findViewById(R.id.nextBtnBottom);
        nextBtnTshirtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadNext();
            }
        });
    }

    private boolean isValidated(TreeMap<String,ArrayList<CategoryBeans>> cList){
        int size =cList.size();
        int count = 0;
        for(ArrayList<CategoryBeans> beans:cList.values()){
            for(CategoryBeans beansList:beans) {
                if (beansList.isSelected()) {
                    count++;
                }
            }
        }
        if(size==count){
            return true;
        }
        return false;
    }
    private void LoadNext(){
        if(Utilities.chooseGrmt.containsKey(Utilities.LastSelectedItem)) {
            TreeMap<String,ArrayList<CategoryBeans>> list = Utilities.chooseGrmt.get(Utilities.LastSelectedItem);
            if(isValidated(list)) {
                startActivity(new Intent(ChooseGarmentsActivity.this, CanvasActivity.class));
                finish();
            }else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void Gramments(){
        ArrayList<CategoryBeans> listOfCat = new ArrayList<>();
        Utilities.chooseGarmentList.put("1",true);
        Utilities.chooseGarmentList.put("2",false);
        Utilities.chooseGarmentList.put("3",false);
        Utilities.chooseGarmentList.put("4",false);
        Utilities.LastSelectedItem = "shirt";
        Utilities.LastSelectedBeans = Utilities.getCategoryBeans(this,"1",R.drawable.cloth_gr,"SHIRT",true);
        listOfCat.add(Utilities.getCategoryBeans(this,"1",R.drawable.cloth_gr,"SHIRT",true));
        listOfCat.add(Utilities.getCategoryBeans(this,"2",R.drawable.tshirt_gr,"T-SHIRT",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"3",R.drawable.pants_gr,"PANTS",false));
        listOfCat.add(Utilities.getCategoryBeans(this,"4",R.drawable.menblazer_gr,"MEN BLAZER",false));
        RecyclerGarmentsAdapter adapter=new RecyclerGarmentsAdapter("garments",listOfCat,this);

        recyclerChooseGarments = findViewById(R.id.chooseGarments);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerChooseGarments.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseGarments.setAdapter(adapter);

    }

    TreeMap<String,ArrayList<CategoryBeans>> listTreeMapShirt = new TreeMap<>();

    private void Collar(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.clr_cr5,"Collar 1",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.clr_cr1,"Collar 2",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.clr_cr2,"Collar 3",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"4",R.drawable.clr_cr3,"Collar 4",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"5",R.drawable.clr_cr4,"Collar Band",false));
        listTreeMapShirt.put("collar",listOfCatCollar);
        Utilities.chooseGrmt.put("shirt",listTreeMapShirt);
        RecyclerChildAdapter adapterCollar=new RecyclerChildAdapter("collar",listOfCatCollar,this);

        recyclerChooseCollar = findViewById(R.id.chooseCollar);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseCollar.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseCollar.setAdapter(adapterCollar);


    }

    private void Seleevs(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.shirt_fullseleeves,"Shirt Full Seleeves",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.shirt_halfseleeves,"Shirt Half Seleeves",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.shirt_smallseleeves,"Shirt Small Seleeves",false));
        listTreeMapShirt.put("seleevs",listOfCatCollar);
        Utilities.chooseGrmt.put("shirt",listTreeMapShirt);
        RecyclerChildAdapter adapterCollar=new RecyclerChildAdapter("seleevs",listOfCatCollar,this);

        recyclerChooseSeleevs = findViewById(R.id.chooseSleeves);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseSeleevs.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseSeleevs.setAdapter(adapterCollar);


    }

    private void Shirt(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.shirt1,"Shirt 1",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.shirt2,"Shirt 2",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.shirt3,"Shirt 3",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"4",R.drawable.shirt4,"Shirt 4",false));
        listTreeMapShirt.put("shirts",listOfCatCollar);
        Utilities.chooseGrmt.put("shirt",listTreeMapShirt);
        RecyclerChildAdapter adapterCollar=new RecyclerChildAdapter("shirts",listOfCatCollar,this);

        recyclerChooseShirts = findViewById(R.id.chooseShirts);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseShirts.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseShirts.setAdapter(adapterCollar);


    }

    private void Cuff(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.cf_barrel,"Barrel Cuff",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.cf_dquare,"Dquare Cuff",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.cf_empty,"Empty Cuff",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"4",R.drawable.cf_french,"French Cuff",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"5",R.drawable.cf_mitered,"Mitered Cuff",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"6",R.drawable.cf_rounded,"Rounded Cuff",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"7",R.drawable.cf_turnback,"Turnback Cuff",false));
        listTreeMapShirt.put("cuff",listOfCatCollar);
        Utilities.chooseGrmt.put("shirt",listTreeMapShirt);


//        RecyclerChildAdapter adapterCollar=new RecyclerChildAdapter("cuff",listOfCatCollar,this);
//
//        recyclerChooseCuff = findViewById(R.id.chooseCuff);
//
//        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
//        recyclerChooseCuff.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
//        recyclerChooseCuff.setAdapter(adapterCollar);


    }

    private void Button(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.btn_metal,"Metal",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.btn_plastic,"Plastic",false));
        listTreeMapShirt.put("button",listOfCatCollar);
        Utilities.chooseGrmt.put("shirt",listTreeMapShirt);

//        RecyclerChildAdapter adapterCollar=new RecyclerChildAdapter("button",listOfCatCollar,this);
//
//        recyclerChooseButtons = findViewById(R.id.chooseButtom);
//
//        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
//        recyclerChooseButtons.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
//        recyclerChooseButtons.setAdapter(adapterCollar);


    }

    private void Long(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.ln_longer,"Longer",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.ln_normal,"Normal",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.ln_hot,"Hot",false));
        listTreeMapShirt.put("long",listOfCatCollar);
        Utilities.chooseGrmt.put("shirt",listTreeMapShirt);

//        RecyclerChildAdapter adapterCollar=new RecyclerChildAdapter("long",listOfCatCollar,this);
//
//        recyclerChooseLong = findViewById(R.id.chooseLong);
//
//        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
//        recyclerChooseLong.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
//        recyclerChooseLong.setAdapter(adapterCollar);


    }


    private void NeckStyle(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.collar1,"Collar band",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.collar2,"O-Collar",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.collar3,"L-Collar",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"4",R.drawable.collar4,"T-Collar",false));
        TreeMap<String,ArrayList<CategoryBeans>> listTreeMap = new TreeMap<>();
        listTreeMapShirt.put("neck",listOfCatCollar);
        Utilities.chooseGrmt.put("tshirt",listTreeMapShirt);

        RecyclerChildAdapter adapterCollar=new RecyclerChildAdapter("neck",listOfCatCollar,this);

        recyclerChooseTShirtNeckStyle = findViewById(R.id.chooseNeckStyle);

        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseTShirtNeckStyle.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseTShirtNeckStyle.setAdapter(adapterCollar);


    }


    private void SleevesTShirt(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.fullseleeves,"Full Seleeves",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.half_seleeves,"Half Seleeves",false));
        listTreeMapShirt.put("tshirt_seleevs",listOfCatCollar);
        Utilities.chooseGrmt.put("tshirt",listTreeMapShirt);

        RecyclerChildAdapter adapterCollar=new RecyclerChildAdapter("tshirt_seleevs",listOfCatCollar,this);
        recyclerChooseTShirtSleeves = findViewById(R.id.chooseShirtSleeves);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseTShirtSleeves.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseTShirtSleeves.setAdapter(adapterCollar);


    }

    private void shirtTShirt(){
        ArrayList<CategoryBeans> listOfCatCollar = new ArrayList<>();
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"1",R.drawable.tshirt,"TShirt 1",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"2",R.drawable.tshirt2,"TShirt 2",false));
        listOfCatCollar.add(Utilities.getCategoryBeans(this,"3",R.drawable.tshirt3,"TShirt 3",false));
        listTreeMapShirt.put("tshirts",listOfCatCollar);
        Utilities.chooseGrmt.put("tshirt",listTreeMapShirt);

        RecyclerChildAdapter adapterCollar=new RecyclerChildAdapter("tshirts",listOfCatCollar,this);
        recyclerChooseTShirt = findViewById(R.id.chooseTShirt);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        recyclerChooseTShirt.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerChooseTShirt.setAdapter(adapterCollar);


    }

}