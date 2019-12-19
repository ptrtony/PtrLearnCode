package com.zhaofan.customview_size_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSquareImageClick(View view){
        startActivity(new Intent(this,SquareImageActivity.class));
    }

    public void onSimpleCircleClick(View view){
        startActivity(new Intent(this,SimpleCircleActivity.class));
    }

    public void onTagLayoutClick(View view){
        startActivity(new Intent(this,TagLayoutActivity.class));
    }
}
