package com.zhaofan.property_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.zhaofan.property_animator.utils.DisplayUtils;
import com.zhaofan.property_animator.view.PointView;

public class BeizerEvaluatorActivity extends AppCompatActivity {
    private static final String TAG = "BeizerEvaluatorActivity";
    PointView pointView;
    View targetView;
    private static PointF controlPoint = new PointF();
    private static final float offsetControlX = DisplayUtils.dpToPx(50);
    private static final float offsetControlY = DisplayUtils.dpToPx(80);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_evaluator);
        pointView = findViewById(R.id.view);
        targetView = findViewById(R.id.targetView);
    }


    private static class PointFEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            //二阶贝塞尔曲线
            Log.d(TAG, "startValueX:" + startValue.x + "---startValueY:" + startValue.y);
            float x = (1 - fraction) * (1 - fraction) * startValue.x + 2 * fraction * (1 - fraction) * controlPoint.x + fraction * fraction * endValue.x;
            float y = (1 - fraction) * (1 - fraction) * startValue.y + 2 * fraction * (1 - fraction) * controlPoint.y + fraction * fraction * endValue.y;
            return new PointF(x, y);
        }
    }


    public void onStartAnimationClick(View view) {
        //终点
        int[] endPointLocation = new int[2];
        targetView.getLocationOnScreen(endPointLocation);
        Log.d(TAG, "outLocationX:" + endPointLocation[0] + "-----outLocationY" + endPointLocation[1]);
        PointF endPointF = new PointF(endPointLocation[0], endPointLocation[1]);

        controlPoint.set(endPointLocation[0] / 2f + offsetControlX, endPointLocation[1] / 2f - offsetControlY);
        //起始点
        PointF startPointF = new PointF(0, 0);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointFEvaluator(), startPointF, endPointF);
        valueAnimator.setStartDelay(1000);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                pointView.setX(pointF.x);
                pointView.setY(pointF.y);
            }
        });
        valueAnimator.start();
    }
}
