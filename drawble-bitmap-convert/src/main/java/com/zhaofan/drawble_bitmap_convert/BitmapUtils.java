package com.zhaofan.drawble_bitmap_convert;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class BitmapUtils {
    /**
     * @param context 上下文
     * @param id      drawable资源id
     * @return
     */
    public static Bitmap drawableToBitmap(Context context, int id) {
        return BitmapFactory.decodeResource(context.getResources(), id);
    }

    /**
     * @param drawable xml文件
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
        }else{
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     *
     * @param context 上下文
     * @param bitmap 位图
     * @return
     */
    public static Drawable bitmapToDrawable(Context context,Bitmap bitmap){
        return new BitmapDrawable(context.getResources(),bitmap);
    }
}
