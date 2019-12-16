package com.zhaofan.property_animator.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.zhaofan.property_animator.utils.BitmapUtils;
import com.zhaofan.property_animator.utils.DisplayUtils;

public class CameraView extends View {
    private static final int IMAGE_WIDTH = DisplayUtils.dpToPx(200);
    private static final int IMAGE_PADDING = DisplayUtils.dpToPx(100);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap image;
    Camera camera = new Camera();
    private float topFlip = 0;
    private float flipRotation = 0;
    private float bottomFlip = 0;

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }



    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        image = BitmapUtils.getAvatar(getContext(), IMAGE_WIDTH);
        camera.rotateX(bottomFlip);
        camera.setLocation(0,0,DisplayUtils.getZForCamera(-6)); //-8*72
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(IMAGE_PADDING+IMAGE_WIDTH/2,IMAGE_PADDING+IMAGE_WIDTH/2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH,-IMAGE_WIDTH,IMAGE_WIDTH,0);
        canvas.rotate(flipRotation);
        canvas.translate(-(IMAGE_PADDING+IMAGE_WIDTH/2),-(IMAGE_PADDING+IMAGE_WIDTH/2));
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(IMAGE_PADDING+IMAGE_WIDTH/2,IMAGE_PADDING+IMAGE_WIDTH/2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(IMAGE_PADDING+IMAGE_WIDTH/2),-(IMAGE_PADDING+IMAGE_WIDTH/2));
        canvas.drawBitmap(image, IMAGE_PADDING, IMAGE_PADDING, paint);
        canvas.restore();
    }
}
