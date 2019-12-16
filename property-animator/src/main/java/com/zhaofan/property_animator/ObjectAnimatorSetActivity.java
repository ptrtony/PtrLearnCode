package com.zhaofan.property_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import com.zhaofan.property_animator.view.CameraView;

public class ObjectAnimatorSetActivity extends AppCompatActivity {
    CameraView cameraView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator_set);
        cameraView = findViewById(R.id.view);
        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(cameraView,"bottomFlip",30);
        bottomFlipAnimator.setDuration(1000);

        ObjectAnimator TopFlipAnimator = ObjectAnimator.ofFloat(cameraView,"topFlip",-30);
        TopFlipAnimator.setDuration(1000);

        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(cameraView,"flipRotation",270);
        flipRotationAnimator.setDuration(1000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnimator,flipRotationAnimator,TopFlipAnimator);
        animatorSet.setStartDelay(1000);
        animatorSet.start();
    }
}
