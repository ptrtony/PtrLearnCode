package com.zhaofan.property_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.widget.ImageView;

import com.zhaofan.property_animator.utils.DisplayUtils;

public class KeyframActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyfram);
        imageView = findViewById(R.id.view);
        float distance = DisplayUtils.dpToPx(300);
        Keyframe keyframe1 = Keyframe.ofFloat(0,0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.1f,0.4f*distance);
        Keyframe keyframe3 = Keyframe.ofFloat(0.9f,0.6f*distance);
        Keyframe keyframe4 = Keyframe.ofFloat(1,distance);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("translationX",
                keyframe1,keyframe2,keyframe3,keyframe4);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView,holder);
        animator.setStartDelay(1000);
        animator.setDuration(1000);
        animator.start();
    }
}
