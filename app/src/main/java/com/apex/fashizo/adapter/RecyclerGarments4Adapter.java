package com.apex.fashizo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apex.fashizo.ChooseGarmentsActivity;
import com.apex.fashizo.R;
import com.apex.fashizo.Utility.Utilities;
import com.apex.fashizo.bean.CategoryBeans;

import java.util.ArrayList;
import java.util.TreeMap;

public class RecyclerGarments4Adapter extends RecyclerView.Adapter<RecyclerGarments4Adapter.RecyclerViewHolder> {

    private ArrayList<CategoryBeans> recyclerDataArrayList;
    private Activity mcontext;
    private ChooseGarmentsActivity mainActivity;
    TreeMap<String,LinearLayout> listOfLay = new TreeMap<>();
    String type = "";

    public RecyclerGarments4Adapter(String type, ArrayList<CategoryBeans> recyclerDataArrayList, Activity activity) {
        this.recyclerDataArrayList = recyclerDataArrayList;
        this.mcontext = activity;
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
//        holder.imgDraw.setImageDrawable(recyclerData.getImage());
        holder.layout.setTag(recyclerData.getId());
        if(recyclerData.isSelected()){
            holder.layout.setBackground(mcontext.getResources().getDrawable(R.drawable.item_sle_selected));
        }else {
            holder.layout.setBackground(mcontext.getResources().getDrawable(R.drawable.item_sle));
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
