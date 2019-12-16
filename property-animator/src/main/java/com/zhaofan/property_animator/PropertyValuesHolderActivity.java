package com.zhaofan.property_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;

import com.zhaofan.property_animator.view.CameraView;

public class PropertyValuesHolderActivity extends AppCompatActivity {

    CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_values_holder);
        cameraView = findViewById(R.id.cameraView);
        PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip",30);
        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip",-30);
        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation",270);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(cameraView,bottomFlipHolder,flipRotationHolder,topFlipHolder);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }
}
