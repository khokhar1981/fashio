package com.apex.fashizo.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.apex.fashizo.WelcomeActivity;
import com.apex.fashizo.bean.CategoryBeans;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<CategoryBeans> courseDataArrayList;
    private Activity mcontext;
    private MainActivity mainActivity;

    public RecyclerViewAdapter(ArrayList<CategoryBeans> recyclerDataArrayList, MainActivity mainActivity) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mainActivity = mainActivity;
        this.mcontext = mainActivity.activity;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        CategoryBeans recyclerData = courseDataArrayList.get(position);
        holder.txtName.setText(recyclerData.getName());
        holder.imgDraw.setImageDrawable(recyclerData.getImage()) ;
        holder.layout.setTag(recyclerData.getId());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = (String)v.getTag();
                LoadActivity(no);
            }
        });

    }

    private void LoadActivity(String no){
            mcontext.startActivity(new Intent(mainActivity, ChooseGarmentsActivity.class));
        mcontext.finish();
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private ImageView imgDraw;
        private LinearLayout layout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtname);
            imgDraw = itemView.findViewById(R.id.image);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
