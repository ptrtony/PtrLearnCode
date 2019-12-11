package com.zhaofan.text_transformation_view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhaofan.text_transformation_view.utils.BitmapUtils;
import com.zhaofan.text_transformation_view.utils.DisplayUtils;

public class ImageTextView extends View {
    private static final int IMAGE_WIDTH = DisplayUtils.dpToPx(150);
    private static final int IMAGE_PADDING = DisplayUtils.dpToPx(80);
    TextPaint textPaint = new TextPaint();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean justo sem, sollicitudin in maximus a, vulputate id magna. Nulla non quam a massa sollicitudin commodo fermentum et est. Suspendisse potenti. Praesent dolor dui, dignissim quis tellus tincidunt, porttitor vulputate nisl. Aenean tempus lobortis finibus. Quisque nec nisl laoreet, placerat metus sit amet, consectetur est. Donec nec quam tortor. Aenean aliquet dui in enim venenatis, sed luctus ipsum maximus. Nam feugiat nisi rhoncus lacus facilisis pellentesque nec vitae lorem. Donec et risus eu ligula dapibus lobortis vel vulputate turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; In porttitor, risus aliquam rutrum finibus, ex mi ultricies arcu, quis ornare lectus tortor nec metus. Donec ultricies metus at magna cursus congue. Nam eu sem eget enim pretium venenatis. Duis nibh ligula, lacinia ac nisi vestibulum, vulputate lacinia tortor.";
    Bitmap image;
    private float[] measureWidth = new float[1];
    private Paint.FontMetrics metrics = new Paint.FontMetrics();
    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        textPaint.setTextSize(30);
        image = BitmapUtils.getAvatar(getContext(), IMAGE_WIDTH);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(DisplayUtils.dpToPx(15));
        paint.getFontMetrics(metrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(image, getWidth() - IMAGE_WIDTH, IMAGE_PADDING, paint);
//        StaticLayout staticLayout = new StaticLayout(text,textPaint,getWidth(), Layout.Alignment.ALIGN_NORMAL,
//                1,0,false);
//        staticLayout.draw(canvas);
        int length = text.length();
        float yOffset = paint.getFontSpacing();
        int useableWidth;
        for (int start = 0, count; start < length; start += count, yOffset += paint.getFontSpacing()) {
            float textTop = metrics.top + yOffset;
            float textBottom = metrics.bottom + yOffset;
            if (textTop>IMAGE_PADDING && textTop<IMAGE_WIDTH + IMAGE_PADDING ||
                    textBottom>IMAGE_PADDING && textBottom<IMAGE_WIDTH + IMAGE_PADDING){
                useableWidth = getWidth()-IMAGE_WIDTH;
            }else{
                useableWidth = getWidth();
            }
            count = paint.breakText(text, start,length,true, useableWidth, measureWidth);
            canvas.drawText(text, start, start + count, 0,yOffset , paint);
        }


    }
}
