package com.zhaofan.multi_touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhaofan.multi_touch.utils.BitmapUtils;
import com.zhaofan.multi_touch.utils.DisplayUtils;

/**
 * 协作型多点触摸
 */
public class MultiTouchView2 extends View {
    private static final int WIDTH = DisplayUtils.dpToPx(200);
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float offsetX, offsetY, downX, downY;
    private float imageOffsetX, imageOffsetY;

    public MultiTouchView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapUtils.getAvatar(context, WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float sumX = 0;
        float sumY = 0;
        boolean isPointerUp = event.getActionMasked() == MotionEvent.ACTION_POINTER_UP;
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            if (!(isPointerUp && event.getActionIndex() == i)){
                sumX += event.getX(i);
                sumY += event.getY(i);
            }
        }
        if (isPointerUp){
            pointerCount--;
        }
        float focusX = sumX / pointerCount;
        float focusY = sumY / pointerCount;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                    downX = focusX;
                    downY = focusY;
                    imageOffsetX = offsetX;
                    imageOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = focusX - downX + imageOffsetX;
                offsetY = focusY - downY + imageOffsetY;
                invalidate();
                break;
        }
        return true;
    }
}
