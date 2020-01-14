package com.zhaofan.viewpager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder> {

    private List<Integer> colors = new ArrayList<>();
    {
        colors.add(android.R.color.black);
        colors.add(android.R.color.holo_purple);
        colors.add(android.R.color.holo_blue_dark);
        colors.add(android.R.color.holo_green_light);
    }


    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page,parent,false);
        return new ViewPagerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {
        holder.mTitle.setText(String.valueOf(position));
        holder.mContainer.setBackgroundColor(holder.mContainer.getResources().getColor(colors.get(position)));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    static class ViewPagerHolder extends RecyclerView.ViewHolder{
        TextView mTitle;
        RelativeLayout mContainer;
        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.tvTitle);
            mContainer = itemView.findViewById(R.id.mContainer);
        }
    }
}
