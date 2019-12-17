package com.zhaofan.drawble_bitmap_convert.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zhaofan.drawble_bitmap_convert.DisplayUtils;

public class MeshDrawable extends Drawable {
    private static final int INTERVAL = DisplayUtils.dpToPx(80);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    {
        paint.setColor(Color.parseColor("#1E88E5"));
        paint.setStrokeWidth(DisplayUtils.dpToPx(5));
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        for (int x=0;x<bounds.right;x+=INTERVAL){
            canvas.drawLine(x,bounds.top,x,bounds.bottom,paint);
        }
        for (int y=0;y<bounds.bottom;y+=INTERVAL){
            canvas.drawLine(bounds.left,y,bounds.right,y,paint);
        }
    }

    /**
     * @param alpha 透明度
     */
    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    /**
     * 不透明
     * @return
     */
    @Override
    public int getOpacity() {
        return paint.getAlpha() == 0xff? PixelFormat.OPAQUE:
                paint.getAlpha() == 0?PixelFormat.TRANSPARENT:
                PixelFormat.TRANSLUCENT;
    }
}
