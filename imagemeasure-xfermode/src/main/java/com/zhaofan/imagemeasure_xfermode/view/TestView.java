package com.zhaofan.imagemeasure_xfermode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhaofan.imagemeasure_xfermode.utils.DisplayUtils;

public class TestView extends View {
    private static final int RADIUS = DisplayUtils.dpToPx(100);
    private Paint paint =  new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    PathMeasure pathMeasure;
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.addCircle(getWidth()/2,getHeight()/2,RADIUS, Path.Direction.CCW);
        path.addRect(getWidth()/2-RADIUS,getHeight()/2,getWidth()/2+RADIUS,getHeight()/2+RADIUS*2,Path.Direction.CCW);
        path.addCircle(getWidth()/2,getHeight()/2,RADIUS*2,Path.Direction.CCW);
        path.setFillType(Path.FillType.EVEN_ODD);
        pathMeasure = new PathMeasure(path,true);
        pathMeasure.getLength();
//        pathMeasure.getPosTan()
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
//        canvas.drawLine(100,100,400,400,paint);
//        canvas.drawCircle(getWidth()/2,getHeight()/2, DisplayUtils.dpToPx(80),paint);
    }
}
