package com.zhaofan.scaleimageview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.OverScroller;

import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.zhaofan.scaleimageview.utils.BitmapUtils;
import com.zhaofan.scaleimageview.utils.DisplayUtils;

public class ScaleImageView extends View {
    private static final float IMAGE_WIDTH = DisplayUtils.dpToPx(300);
    private static final float OVER_SCALE_FACTOR = 1.5f;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private float originOffsetX, originOffsetY;
    private float offsetX, offsetY;
    private float bigScale, smallScale;
    private GestureDetectorCompat detector;
    private boolean big = false;
    private float currentScale;
    private ObjectAnimator scaleAnimator;
    private OverScroller scroller;
    PtrGestureDetectorListener gestureDetectorListener = new PtrGestureDetectorListener();
    private PtrFlingRunner flingRunner = new PtrFlingRunner();
    private ScaleGestureDetector scaleGestureDetector;
    PtrScaleGestureDetectorListener scaleGestureDetectorListener = new PtrScaleGestureDetectorListener();

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapUtils.getAvatar(context, (int) IMAGE_WIDTH);
        detector = new GestureDetectorCompat(context, gestureDetectorListener);
        scroller = new OverScroller(context);
        scaleGestureDetector = new ScaleGestureDetector(context, scaleGestureDetectorListener);
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setCurrentScale(float currentScale) {
        this.currentScale = currentScale;
        invalidate();
    }

    private ObjectAnimator getScaleAnimator() {
        if (scaleAnimator == null) {
            scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", 0);
        }
        scaleAnimator.setFloatValues(smallScale,bigScale);
        return scaleAnimator;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originOffsetX = (getWidth() - bitmap.getWidth()) / 2f;
        originOffsetY = (getHeight() - bitmap.getHeight()) / 2f;
        if ((float) bitmap.getWidth() / bitmap.getHeight() > (float) getWidth() / getHeight()) {
            smallScale = (float) getWidth() / bitmap.getWidth();
            bigScale = (float) getHeight() / bitmap.getHeight() * OVER_SCALE_FACTOR;
        } else {
            smallScale = (float) getHeight() / bitmap.getHeight();
            bigScale = (float) getWidth() / bitmap.getWidth() * OVER_SCALE_FACTOR;
        }
        currentScale = smallScale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * scaleFraction, offsetY * scaleFraction);
        canvas.scale(currentScale, currentScale, getWidth() / 2f, getHeight() / 2f);
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = scaleGestureDetector.onTouchEvent(event);
        if (!scaleGestureDetector.isInProgress()) {
            result = detector.onTouchEvent(event);
        }
        return result;
    }


    private void fixOffset() {
        if (!big){
            float scaleFraction = (currentScale - smallScale) / (bigScale - smallScale);
            offsetX = Math.min(offsetX, (bitmap.getWidth() * scaleFraction- getWidth()) / 2);
            offsetX = Math.max(offsetX, -(bitmap.getWidth() * scaleFraction - getWidth()) / 2);
            offsetY = Math.min(offsetY, (bitmap.getHeight() * scaleFraction - getHeight()) / 2);
            offsetY = Math.max(offsetY, -(bitmap.getHeight() * scaleFraction - getHeight()) / 2);
        }else{
            offsetX = Math.min(offsetX, (bitmap.getWidth() * bigScale - getWidth()) / 2);
            offsetX = Math.max(offsetX, -(bitmap.getWidth() * bigScale - getWidth()) / 2);
            offsetY = Math.min(offsetY, (bitmap.getHeight() * bigScale - getHeight()) / 2);
            offsetY = Math.max(offsetY, -(bitmap.getHeight() * bigScale - getHeight()) / 2);
        }
    }

    private class PtrGestureDetectorListener implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (big) {
                offsetX -= distanceX;
                offsetY -= distanceY;
                fixOffset();
                invalidate();
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (big) {
                scroller.fling((int) offsetX, (int) offsetY, (int) velocityX, (int) velocityY, -(int) (bitmap.getWidth() * bigScale - getWidth()) / 2,
                        (int) (bitmap.getWidth() * bigScale - getWidth()) / 2, -(int) (bitmap.getHeight() * bigScale - getHeight()) / 2,
                        (int) (bitmap.getHeight() * bigScale - getHeight()) / 2);
                postOnAnimation(flingRunner);
            }
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            big = !big;
            if (big) {
                offsetX = (e.getX() - getWidth() / 2f) * (1 - bigScale / smallScale);
                offsetY = (e.getY() - getHeight() / 2f) * (1 - bigScale / smallScale);
                fixOffset();
                getScaleAnimator().start();
            } else {
                getScaleAnimator().reverse();
            }
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }

    }

    private class PtrFlingRunner implements Runnable {
        @Override
        public void run() {
            refresh();
        }
    }

    private void refresh() {
        if (scroller.computeScrollOffset()) {
            offsetX = scroller.getCurrX();
            offsetY = scroller.getCurrY();
            invalidate();
            postOnAnimation(flingRunner);
        }
    }

    private class PtrScaleGestureDetectorListener implements ScaleGestureDetector.OnScaleGestureListener {
        float initialScale;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            if (big){
                currentScale = initialScale * detector.getScaleFactor();
                if (currentScale>bigScale*5){
                    currentScale = bigScale*5;
                }else if (currentScale<smallScale){
                    currentScale = smallScale;
                }
                invalidate();
            }
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            initialScale = currentScale;
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {

        }
    }


}
