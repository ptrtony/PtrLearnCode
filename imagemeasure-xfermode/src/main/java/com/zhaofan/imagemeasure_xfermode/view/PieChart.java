package com.zhaofan.imagemeasure_xfermode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhaofan.imagemeasure_xfermode.utils.DisplayUtils;

public class PieChart extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int RADIUS = DisplayUtils.dpToPx(150);
    private static final int PULLED_LEGHT = DisplayUtils.dpToPx(20);

    private static int PULLED_INDEX = 1;

    RectF bounds = new RectF();
    int[] colors = new int[]{
            Color.parseColor("#448AFF"),
            Color.parseColor("#D81B60"),
            Color.parseColor("#43A037"),
            Color.parseColor("#FDD835")
    };

    int[] angles = new int[]{
            60,100,120,80
    };

    public PieChart(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bounds.set(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS);

    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currentAngle = 0;
        for (int i=0;i<colors.length;i++){
            paint.setColor(colors[i]);
            if (PULLED_INDEX == i){
                canvas.save();
                canvas.translate((float)Math.cos(Math.toRadians(currentAngle + angles[i]/2))*PULLED_LEGHT,
                        (float)Math.sin(Math.toRadians(currentAngle + angles[i]/2))*PULLED_LEGHT);
            }
            canvas.drawArc(bounds,currentAngle,angles[i],true,paint);
            if (PULLED_INDEX == i){
                canvas.restore();
            }
            currentAngle+=angles[i];
        }
    }
}
