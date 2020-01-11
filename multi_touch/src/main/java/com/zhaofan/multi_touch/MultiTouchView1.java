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
 *
 */
public class MultiTouchView1 extends View {
    private static final int WIDTH = DisplayUtils.dpToPx(200);
    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float offsetX,offsetY,downX,downY;
    private float imageOffsetX,imageOffsetY;
    private int trackingPointerId;
    public MultiTouchView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapUtils.getAvatar(context,WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,offsetX,offsetY,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                trackingPointerId = event.getPointerId(0);
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                imageOffsetX = offsetX;
                imageOffsetY = offsetY;
                break;
            case MotionEvent.ACTION_MOVE:
                int index = event.findPointerIndex(trackingPointerId);
                offsetX = event.getX(index) - downX + imageOffsetX;
                offsetY = event.getY(index) - downY + imageOffsetY;
                invalidate();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int actionId = event.getActionIndex();
                trackingPointerId = event.getPointerId(actionId);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                actionId = event.getActionIndex();
                int pointerId = event.getPointerId(actionId);
                if (pointerId == trackingPointerId){
                    int newIndex;
                    if (actionId == event.getPointerCount()-1){
                        newIndex = event.getPointerCount()-2;
                    }else{
                        newIndex = event.getPointerCount()-1;
                    }
                    trackingPointerId = event.getPointerId(newIndex);
                    downX = event.getX(newIndex);
                    downY = event.getY(newIndex);
                    imageOffsetX = offsetX;
                    imageOffsetY = offsetY;
                }
                break;
        }
        return true;
    }
}
