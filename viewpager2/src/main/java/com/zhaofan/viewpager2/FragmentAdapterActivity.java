package com.zhaofan.viewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class FragmentAdapterActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private TableLayout mTabLayout;
    public static List<Integer> colors = new ArrayList<>();

    private ViewPagerFragmentStateAdapter mAdapter;
    {
        colors.add(android.R.color.black);
        colors.add(android.R.color.holo_purple);
        colors.add(android.R.color.holo_blue_dark);
        colors.add(android.R.color.holo_green_light);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_adapter);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager2 = findViewById(R.id.viewPager);
        mAdapter = new ViewPagerFragmentStateAdapter(getSupportFragmentManager());
        mViewPager2.setAdapter(mAdapter);
//        mTabLayout.addTab(mTabLayout.newTab().setText("Tab0"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("Tab1"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("Tab2"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("Tab3"));
//        // 添加页签选中监听
//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                mViewPager2.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {}
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {}
//        });
//        // 注册页面变化的回调接口
//        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                mTabLayout.setScrollPosition(position,0,false);
//            }
//        });
    }
}
