package com.zhaofan.property_animator.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhaofan.property_animator.utils.DisplayUtils;

public class CircleView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float radius = DisplayUtils.dpToPx(50);
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
    }


    public void setRadius(float radius){
        this.radius = radius;
        invalidate();
    }

    public float getRadius(){
        return radius;
    }
}
