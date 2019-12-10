package com.zhaofan.imagemeasure_xfermode.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zhaofan.imagemeasure_xfermode.R;

public class BitmapUtils {
    public static Bitmap getAvatar(Context context, int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(),R.mipmap.avatar,options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(context.getResources(),R.mipmap.avatar,options);
    }
}
