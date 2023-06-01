package com.apex.fashizo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apex.fashizo.CanvasActivity;
import com.apex.fashizo.R;
import com.apex.fashizo.Utility.Utilities;
import com.apex.fashizo.bean.CategoryBeans;

import java.util.ArrayList;
import java.util.TreeMap;

public class RecyclerMenuAdapter extends RecyclerView.Adapter<RecyclerMenuAdapter.RecyclerViewHolder> {

    private ArrayList<CategoryBeans> recyclerDataArrayList;
    private CanvasActivity mcontext;
    TreeMap<String,LinearLayout> listOfLay = new TreeMap<>();
    String type = "";

    public RecyclerMenuAdapter(String type, ArrayList<CategoryBeans> recyclerDataArrayList, CanvasActivity mainActivity) {
        this.recyclerDataArrayList = recyclerDataArrayList;
        this.mcontext = mainActivity;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_menu, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        CategoryBeans recyclerData = recyclerDataArrayList.get(position);
        holder.menuName.setText(recyclerData.getName());
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
        if(Utilities.chooseGrmt.containsKey(Utilities.LastSelectedItem)) {
            TreeMap<String,ArrayList<CategoryBeans>> list = Utilities.chooseGrmt.get(Utilities.LastSelectedItem);
            if(list.containsKey(type)) {
                ArrayList<CategoryBeans> cBeansList = list.get(type);
                int index = Integer.parseInt(id) - 1;
                for (int indexIn = 0; indexIn < cBeansList.size(); indexIn++) {
                    CategoryBeans beans = cBeansList.get(indexIn);
                    if (indexIn == index) {
                        beans.setSelected(true);
                    } else {
                        beans.setSelected(false);
                    }
                }
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

        int indexKey = Integer.parseInt(id);
        mcontext.ChangeSubMenu(indexKey);

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return recyclerDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView menuName;
        private LinearLayout layout;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.menuName);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
