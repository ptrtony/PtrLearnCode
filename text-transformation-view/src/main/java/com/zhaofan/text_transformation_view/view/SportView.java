package com.zhaofan.text_transformation_view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.zhaofan.text_transformation_view.utils.DisplayUtils;

public class SportView extends View {
    private static final int CIRCLE_COLOR = Color.parseColor("#96A5AD");
    private static final int RING_WIDTH = DisplayUtils.dpToPx(20);
    private static final int HIGHLIGHT_COLOR = Color.parseColor("#FF4081");
    private static final int RADIUS = DisplayUtils.dpToPx(150);
    private static final int TEXT_WITH = DisplayUtils.dpToPx(2);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect bound = new Rect();
    Paint.FontMetrics metrics = new Paint.FontMetrics();
    public SportView(Context context) {
        super(context);
    }

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SportView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(100);
        paint.setTypeface(Typeface.createFromAsset(getResources().getAssets(),"Quicksand-Regular.ttf"));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制环
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(CIRCLE_COLOR);
        paint.setStrokeWidth(RING_WIDTH);
        canvas.drawCircle(getWidth()/2,getHeight()/2,RADIUS,paint);

        //绘制进度条
        paint.setColor(HIGHLIGHT_COLOR);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth()/2-RADIUS,getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,getHeight()/2+RADIUS,-90,225,false,paint);
        paint.setStrokeCap(Paint.Cap.BUTT);

        //绘制文字
        paint.setStrokeWidth(TEXT_WITH);
        paint.setStyle(Paint.Style.FILL);
        String text  = "abab";
//        paint.getTextBounds(text,0,text.length(),bound);
//        float offset = (bound.top + bound.bottom)/2f;
        paint.getFontMetrics(metrics);
        float offset = (metrics.ascent+metrics.descent)/2;
        canvas.drawText(text,getWidth()/2,getHeight()/2 - offset,paint);

        //绘制文字：贴边
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(DisplayUtils.dpToPx(150));
        paint.getTextBounds(text,0,text.length(),bound);
        canvas.drawText(text,-bound.left,-bound.top,paint);
        paint.setTextSize(DisplayUtils.dpToPx(15));
        paint.getTextBounds(text,0,text.length(),bound);
        canvas.drawText(text,-bound.left,-bound.top+paint.getFontSpacing(),paint);

    }
}
