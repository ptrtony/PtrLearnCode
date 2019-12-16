package com.zhaofan.property_animator.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zhaofan.property_animator.utils.DisplayUtils;

public class ProvinceView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private String province = "北京市";

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        invalidate();
        this.province = province;
    }

    public ProvinceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setTextSize(DisplayUtils.dpToPx(30));
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(province, getWidth() / 2, getHeight() / 2, paint);
    }
}
