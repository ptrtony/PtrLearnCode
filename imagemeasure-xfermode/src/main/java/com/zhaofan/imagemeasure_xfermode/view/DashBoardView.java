package com.zhaofan.imagemeasure_xfermode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.zhaofan.imagemeasure_xfermode.utils.DisplayUtils;

public class DashBoardView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int RADIUS = DisplayUtils.dpToPx(150);
    private static final int ANGLE = 120;
    private static final int LEGHT = DisplayUtils.dpToPx(100);
    PathDashPathEffect pathDashPathEffect;
    Path dash = new Path();
    Path path = new Path();
//    Paint pointer = new Paint(Paint.ANTI_ALIAS_FLAG);
    PathMeasure pathMeasure ;
    public DashBoardView(Context context) {
        super(context);
    }

    public DashBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DashBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);

//        pointer.setStrokeWidth(4);
//        pointer.setStyle(Paint.Style.STROKE);

        dash.addRect(0,0,DisplayUtils.dpToPx(2),DisplayUtils.dpToPx(10), Path.Direction.CCW);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        path.addArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS,90+ANGLE/2,
                360-ANGLE);
        pathMeasure = new PathMeasure();
        pathMeasure.setPath(path,false);

        pathDashPathEffect = new PathDashPathEffect(dash,(pathMeasure.getLength()-DisplayUtils.dpToPx(2))/20,0, PathDashPathEffect.Style.ROTATE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //原图形
        canvas.drawArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS,90+ANGLE/2,
                360-ANGLE,false,paint);

        paint.setPathEffect(pathDashPathEffect);
        //画刻度
        canvas.drawArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS,90+ANGLE/2,
                360-ANGLE,false,paint);
        paint.setPathEffect(null);
        //画指针
        canvas.drawLine(getWidth()/2,getHeight()/2,
                getWidth()/2+(float)Math.cos(Math.toRadians(getAngleForMark(5)))*LEGHT,
                getHeight()/2 + (float)Math.sin(Math.toRadians(getAngleForMark(5)))*LEGHT,paint);


    }


    private float getAngleForMark(int mark){
        return 90 + ANGLE/2 + (360 - ANGLE)/20 * mark;
    }
}
