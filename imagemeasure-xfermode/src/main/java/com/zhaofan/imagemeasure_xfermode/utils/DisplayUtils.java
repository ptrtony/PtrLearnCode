package com.zhaofan.imagemeasure_xfermode.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class DisplayUtils {
    public static int dpToPx(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
