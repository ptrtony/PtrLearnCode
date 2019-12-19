package com.zhaofan.customview_size_layout.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhaofan.customview_size_layout.utils.DisplayUtils;

public class CircleView extends View {
    private static final float PADDING = DisplayUtils.dpToPx(20);
    float radius = DisplayUtils.dpToPx(80);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = (int) ((PADDING + radius) * 2);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        int practicalWidth = getSize(size,widthMode);
//        int practicalHeight = getSize(size,heightMode);
        int practicalWidth = resolveSize(size,widthMeasureSpec);
        int practicalHeight = resolveSize(size,heightMeasureSpec);
        setMeasuredDimension(practicalWidth, practicalHeight);
    }

    private int getSize(int currentSize,int mode){
        int exactlySize = MeasureSpec.getSize(mode);
        int measureSize = 0;
        switch (mode){
            case MeasureSpec.EXACTLY:
                measureSize = exactlySize;
                break;
            case MeasureSpec.AT_MOST:
                if (currentSize>exactlySize){
                    measureSize = exactlySize;
                }else{
                    measureSize = currentSize;
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                measureSize = currentSize;
                break;
        }
        return measureSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(PADDING + radius, PADDING + radius, radius, paint);
    }
}