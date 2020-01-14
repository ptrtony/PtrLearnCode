package com.zhaofan.viewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class HorizonScrollingActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    public static void startActivity(Context context){
        Intent intent = new Intent(context,HorizonScrollingActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizon_scrolling);
        viewPager2 = findViewById(R.id.viewpager2);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager2.setAdapter(viewPagerAdapter);
    }
}
