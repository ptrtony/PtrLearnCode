package com.zhaofan.property_animator;

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

    public void onAnimateClick(View view) {
        startActivity(new Intent(this, AnimateActivity.class));
    }

    public void onObjectAnimatorClick(View view) {
        startActivity(new Intent(this, ObjectAnimatorActivity.class));
    }

    public void onObjectAnimatorSetClick(View view) {
        startActivity(new Intent(this, ObjectAnimatorSetActivity.class));
    }

    public void onPropertyValuesHolderClick(View view) {
        startActivity(new Intent(this, PropertyValuesHolderActivity.class));
    }

    public void onKeyframeClick(View view) {
        startActivity(new Intent(this, KeyframActivity.class));

    }

    public void onBeizerEvaluatorClick(View view) {
        startActivity(new Intent(this, BeizerEvaluatorActivity.class));
    }

    public void onTextEvaluatorClick(View view) {
        startActivity(new Intent(this, TextEvaluatorActivity.class));
    }


}
