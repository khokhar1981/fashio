package com.apex.fashizo.Utility;

import android.app.Activity;
import android.graphics.drawable.Drawable;

import com.apex.fashizo.R;
import com.apex.fashizo.bean.CategoryBeans;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class Utilities {

    public static TreeMap<String,TreeMap<String, ArrayList<CategoryBeans>>> chooseGrmt = new TreeMap<>();
    public static TreeMap<String,Boolean> chooseGarmentList = new TreeMap<>();
    public static String LastSelectedItem = "shirt";
    public static CategoryBeans LastSelectedBeans = null;
    public static CategoryBeans getCategoryBeans(Activity activity,String id, int image, String title,boolean isSelected){
        CategoryBeans beans =  null;
        try {
            beans = new CategoryBeans();
            beans.setId(id);
            beans.setName(title);
            beans.setSelected(isSelected);
            beans.setImages(image);

        }catch(Exception ex){

        }
        return beans;
    }
    public static Drawable getDrawable(Activity activity, String name){
        Drawable drawable = null;
        try{
            InputStream ims = activity.getAssets().open(name);
            // load image as Drawable
            drawable = Drawable.createFromStream(ims, null);
            // set image to ImageView

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return drawable;
    }

    public static int getSelectedImage(ArrayList<CategoryBeans> beansList){
        int image=0;
        for(CategoryBeans beans:beansList){
            if(beans.isSelected()){
                image = beans.getImages();
            }
        }
        return image;
    }
}
