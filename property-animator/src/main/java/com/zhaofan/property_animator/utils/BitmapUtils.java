package com.zhaofan.property_animator.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.zhaofan.property_animator.R;

public class BitmapUtils {
    public static Bitmap getAvatar(Context context, int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), R.mipmap.animator,options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(context.getResources(),R.mipmap.animator,options);
    }
}
