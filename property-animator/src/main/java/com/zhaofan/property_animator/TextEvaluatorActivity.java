package com.zhaofan.property_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import com.zhaofan.property_animator.utils.ProvinceUtils;
import com.zhaofan.property_animator.view.ProvinceView;

public class TextEvaluatorActivity extends AppCompatActivity {
    ProvinceView provinceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_evaluator);
        provinceView = findViewById(R.id.view);

        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(provinceView,"province", new ProvinceUtils.ProvinceEvaluator(),"澳门特别行政区");
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(10000);
        objectAnimator.start();
    }
}
