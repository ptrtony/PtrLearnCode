package com.zhaofan.imagemeasure_xfermode.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.zhaofan.imagemeasure_xfermode.utils.BitmapUtils;
import com.zhaofan.imagemeasure_xfermode.utils.DisplayUtils;

public class AvatarView extends View {
    private static final float WIDTH = DisplayUtils.dpToPx(300);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int PADDING = DisplayUtils.dpToPx(40);
    private static final int BORDER_WIDTH = DisplayUtils.dpToPx(10);
    private Bitmap avatar;
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    RectF cut;
    RectF border;
    public AvatarView(Context context) {
        super(context);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        avatar = BitmapUtils.getAvatar(getContext(), (int) WIDTH);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cut = new RectF(PADDING,PADDING,PADDING+WIDTH,PADDING+WIDTH);
        border = new RectF(PADDING - BORDER_WIDTH,PADDING - BORDER_WIDTH,PADDING+BORDER_WIDTH+WIDTH,PADDING+BORDER_WIDTH+WIDTH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(border,paint);
        int saveCount = canvas.saveLayer(cut,paint);
        canvas.drawOval(cut,paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(avatar,PADDING,PADDING,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saveCount);
    }
}
