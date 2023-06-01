package com.apex.fashizo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apex.fashizo.ChooseGarmentsActivity;
import com.apex.fashizo.MainActivity;
import com.apex.fashizo.R;
import com.apex.fashizo.Utility.Utilities;
import com.apex.fashizo.bean.CategoryBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RecyclerGarmentsAdapter extends RecyclerView.Adapter<RecyclerGarmentsAdapter.RecyclerViewHolder> {

    private ArrayList<CategoryBeans> recyclerDataArrayList;
    private Activity mcontext;
    TreeMap<String,LinearLayout> listOfLay = new TreeMap<>();
    String type = "";

    public RecyclerGarmentsAdapter(String type,ArrayList<CategoryBeans> recyclerDataArrayList, Activity mainActivity) {
        this.recyclerDataArrayList = recyclerDataArrayList;
        this.mcontext = mainActivity;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_graments, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        CategoryBeans recyclerData = recyclerDataArrayList.get(position);
        holder.imgDraw.setImageDrawable(mcontext.getResources().getDrawable(recyclerData.getImages()));
        holder.layout.setTag(recyclerData.getId());
        listOfLay.put(recyclerData.getId(),holder.layout);
        if(recyclerData.isSelected()){
            holder.layout.setBackground(mcontext.getResources().getDrawable(R.drawable.item_sle_selected));

        }else {
            holder.layout.setBackground(mcontext.getResources().getDrawable(R.drawable.item_sle));
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String)v.getTag();
                changeList(holder,id);

            }
        });
    }

    private void changeList(@NonNull RecyclerViewHolder holder,String id){

        if(ChooseGarmentsActivity.getChooseGarmentsActivity()!=null) {
            int index=Integer.parseInt(id)-1;
            if (id == "1") {
                ChooseGarmentsActivity.getChooseGarmentsActivity().changeLayoutStatus("shirt");
                Utilities.chooseGarmentList.put("1",true);
                Utilities.chooseGarmentList.put("2",false);
                Utilities.chooseGarmentList.put("3",false);
                Utilities.chooseGarmentList.put("4",false);
                Utilities.LastSelectedItem = "shirt";

                Utilities.LastSelectedBeans = recyclerDataArrayList.get(index);
            }else if (id == "2") {
                ChooseGarmentsActivity.getChooseGarmentsActivity().changeLayoutStatus("tshirt");
                Utilities.chooseGarmentList.put("1",false);
                Utilities.chooseGarmentList.put("2",true);
                Utilities.chooseGarmentList.put("3",false);
                Utilities.chooseGarmentList.put("4",false);
                Utilities.LastSelectedItem = "tshirt";
                Utilities.LastSelectedBeans = recyclerDataArrayList.get(index);
            }else if (id == "3") {
         //       ChooseGarmentsActivity.getChooseGarmentsActivity().changeLayoutStatus("pents");
                Utilities.chooseGarmentList.put("1",false);
                Utilities.chooseGarmentList.put("2",false);
                Utilities.chooseGarmentList.put("3",true);
                Utilities.chooseGarmentList.put("4",false);
                Utilities.LastSelectedItem = "pents";
            }else if (id == "4") {
                Utilities.chooseGarmentList.put("1",false);
                Utilities.chooseGarmentList.put("2",false);
                Utilities.chooseGarmentList.put("3",false);
                Utilities.chooseGarmentList.put("4",true);
                Utilities.LastSelectedItem = "menblazer";
                //     ChooseGarmentsActivity.getChooseGarmentsActivity().changeLayoutStatus("menblazer");
            }
        }

        for(String key:listOfLay.keySet()){
            listOfLay.get(key).setPadding(10,10,10,10);
            if(key.equalsIgnoreCase(id)){
                listOfLay.get(key).setBackground(mcontext.getResources().getDrawable(R.drawable.item_sle_selected));
            }else {
                listOfLay.get(key).setBackground(mcontext.getResources().getDrawable(R.drawable.item_sle));
            }



        }

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return recyclerDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgDraw;
        private LinearLayout layout;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDraw = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
