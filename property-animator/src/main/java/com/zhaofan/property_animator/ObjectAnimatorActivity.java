package com.zhaofan.property_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import com.zhaofan.property_animator.utils.DisplayUtils;
import com.zhaofan.property_animator.view.CircleView;

/**
 * objectAnimator  动画
 */
public class ObjectAnimatorActivity extends AppCompatActivity {

    CircleView circleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        circleView = findViewById(R.id.view);
        ObjectAnimator animator = ObjectAnimator.ofFloat(circleView,"radius", DisplayUtils.dpToPx(150));
        animator.setDuration(1500);
        animator.start();
    }
}
