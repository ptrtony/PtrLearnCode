package com.zhaofan.multi_touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhaofan.multi_touch.utils.BitmapUtils;
import com.zhaofan.multi_touch.utils.DisplayUtils;

/**
 * 多点触摸互相无干扰
 */
public class MultiTouchView3 extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    SparseArray<Path> paths = new SparseArray<>();
    public MultiTouchView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DisplayUtils.dpToPx(4));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            Path path = paths.valueAt(i);
            canvas.drawPath(path,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionIndex = event.getActionIndex();
                int pointerId = event.getPointerId(actionIndex);
                Path path = new Path();
                path.moveTo(event.getX(actionIndex),event.getY(actionIndex));
                paths.append(pointerId,path);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                int painterId;
                for (int i = 0; i < event.getPointerCount(); i++) {
                    painterId = event.getPointerId(i);
                    path = paths.get(painterId);
                    path.lineTo(event.getX(i),event.getY(i));
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                painterId = event.getPointerId(event.getActionIndex());
                paths.remove(painterId);
                invalidate();
                break;

        }
        return true;
    }
}
