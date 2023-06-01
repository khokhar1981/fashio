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

import com.apex.fashizo.ChooseGarmentsActivity;
import com.apex.fashizo.R;
import com.apex.fashizo.Utility.Utilities;
import com.apex.fashizo.bean.CategoryBeans;

import java.util.ArrayList;
import java.util.TreeMap;

public class RecyclerChildAdapter extends RecyclerView.Adapter<RecyclerChildAdapter.RecyclerViewHolder> {

    private ArrayList<CategoryBeans> recyclerDataArrayList;
    private Activity mcontext;
    TreeMap<String,LinearLayout> listOfLay = new TreeMap<>();
    String type = "";

    public RecyclerChildAdapter(String type, ArrayList<CategoryBeans> recyclerDataArrayList, Activity mainActivity) {
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
        holder.textView.setText(recyclerData.getName());
        listOfLay.put(recyclerData.getId(),holder.layout);
        if(recyclerData.isSelected()){
            holder.layout.setBackground(mcontext.getResources().getDrawable(R.drawable.item_sle_selected));
            holder.textView.setVisibility(View.VISIBLE);
        }else {
            holder.layout.setBackground(mcontext.getResources().getDrawable(R.drawable.item_sle));
            holder.textView.setVisibility(View.GONE);
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
//        if(type.equalsIgnoreCase("garments")) {
//            for (String key : Utilities.chooseGarmentList.keySet()) {
//                if (key.equalsIgnoreCase(id)) {
//                    Utilities.chooseGarmentList.put(id, true);
//                } else {
//                    Utilities.chooseGarmentList.put(key, false);
//                }
//            }
//        }else if(type.equalsIgnoreCase("collar")) {
//            for (String key : Utilities.chooseCollarList.keySet()) {
//                if (key.equalsIgnoreCase(id)) {
//                    Utilities.chooseCollarList.put(id, true);
//                } else {
//                    Utilities.chooseCollarList.put(key, false);
//                }
//            }
//        }else if(type.equalsIgnoreCase("seleevs")) {
//            for (String key : Utilities.chooseSeleevsList.keySet()) {
//                if (key.equalsIgnoreCase(id)) {
//                    Utilities.chooseSeleevsList.put(id, true);
//                } else {
//                    Utilities.chooseSeleevsList.put(key, false);
//                }
//            }
//        }else if(type.equalsIgnoreCase("cuff")) {
//            for (String key : Utilities.chooseCuffList.keySet()) {
//                if (key.equalsIgnoreCase(id)) {
//                    Utilities.chooseCuffList.put(id, true);
//                } else {
//                    Utilities.chooseCuffList.put(key, false);
//                }
//            }
//        }else if(type.equalsIgnoreCase("button")) {
//            for (String key : Utilities.chooseButtonList.keySet()) {
//                if (key.equalsIgnoreCase(id)) {
//                    Utilities.chooseButtonList.put(id, true);
//                } else {
//                    Utilities.chooseButtonList.put(key, false);
//                }
//            }
//        }else if(type.equalsIgnoreCase("long")) {
//            for (String key : Utilities.chooseLongList.keySet()) {
//                if (key.equalsIgnoreCase(id)) {
//                    Utilities.chooseLongList.put(id, true);
//                } else {
//                    Utilities.chooseLongList.put(key, false);
//                }
//            }
//        }


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
        private TextView textView;
        private LinearLayout layout;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDraw = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.menuName);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
