package com.zhaofan.drawble_bitmap_convert.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

import com.zhaofan.drawble_bitmap_convert.drawable.MeshDrawable;

public class DrawableView extends View {
    Drawable drawable;
    public DrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        drawable = new MeshDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }
}
