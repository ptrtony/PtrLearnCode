package com.zhaofan.property_animator.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhaofan.property_animator.utils.DisplayUtils;

public class PointView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float RADIUS = DisplayUtils.dpToPx(20);
    PointF pointF = new PointF(0,0);

    public PointF getPointF() {
        return pointF;
    }

    public void setPointF(PointF pointF) {
        this.pointF = pointF;
        invalidate();
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setStrokeWidth(RADIUS);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(pointF.x+RADIUS/2,pointF.x + RADIUS/2,paint);
    }
}
