package com.zhaofan.property_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import com.zhaofan.property_animator.utils.DisplayUtils;

/**
 * ViewPropertyAnimator 动画
 */
public class AnimateActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        imageView = findViewById(R.id.view);
        imageView.animate()
                .translationX(DisplayUtils.dpToPx(200))
                .setStartDelay(1000)
                .setDuration(1000)
                .start();

    }
}
