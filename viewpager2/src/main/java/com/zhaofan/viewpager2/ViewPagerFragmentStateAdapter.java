package com.zhaofan.viewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import static com.zhaofan.viewpager2.FragmentAdapterActivity.colors;

public class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {
    public ViewPagerFragmentStateAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return PageFragment.getNewInstance(colors,position);
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }
}
