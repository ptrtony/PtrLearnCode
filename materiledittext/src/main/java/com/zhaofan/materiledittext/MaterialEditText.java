package com.zhaofan.materiledittext;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.zhaofan.materiledittext.utils.DisplayUtils;

public class MaterialEditText extends AppCompatEditText {
    private static final int TEXT_SIZE = DisplayUtils.dpToPx(12);
    private static final int TEXT_MARGIN = DisplayUtils.dpToPx(8);
    private static final int VERTICAL_OFFSET = DisplayUtils.dpToPx(38);
    private static final int HORIZONTAL_OFFSET = DisplayUtils.dpToPx(5);
    private static final int VERTICAL_OFFSET_EXTRA = DisplayUtils.dpToPx(16);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Boolean flowLabShow = false;
    private ObjectAnimator animator;
    private float flowLabFraction;
    private boolean useFloatingLabel = false;
    private Rect backgroundPadding = new Rect();
    public float getFlowLabFraction() {
        return flowLabFraction;
    }

    public void setFlowLabFraction(float flowLabFraction) {
        this.flowLabFraction = flowLabFraction;
        invalidate();
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }


    void init(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MaterialEditText);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel,false);
        typedArray.recycle();

        paint.setTextSize(TEXT_SIZE);
//        setPadding(getPaddingLeft(),getPaddingTop()+TEXT_SIZE+TEXT_MARGIN,getPaddingRight(),getPaddingBottom());
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flowLabShow && TextUtils.isEmpty(s)){
                    flowLabShow = !flowLabShow;
                    getAnimator().reverse();
                }else if (!flowLabShow && !TextUtils.isEmpty(s)){
                    flowLabShow = !flowLabShow;
                    getAnimator().start();
                }
            }
        });

        setUseFloatingLabel(useFloatingLabel);
    }

    public void setUseFloatingLabel(boolean useFloatingLabel){
        if (this.useFloatingLabel!=useFloatingLabel){
            this.useFloatingLabel = useFloatingLabel;
            getBackground().getPadding(backgroundPadding);//通过该方法可以固定padding的大小。
            if (useFloatingLabel){
                setPadding(backgroundPadding.left,backgroundPadding.top+TEXT_SIZE+TEXT_MARGIN,backgroundPadding.right,backgroundPadding.bottom);
            }else{
                setPadding(backgroundPadding.left,backgroundPadding.top,backgroundPadding.right,backgroundPadding.bottom);
            }
        }
    }

    private ObjectAnimator getAnimator(){
        if (animator == null){
            animator = ObjectAnimator.ofFloat(MaterialEditText.this,"flowLabFraction",0,1);
            animator.start();
        }
        return animator;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (useFloatingLabel){
            paint.setAlpha((int) (flowLabFraction*0xff));
            if (!TextUtils.isEmpty(getText())){
                canvas.drawText(getHint().toString(),HORIZONTAL_OFFSET,VERTICAL_OFFSET - VERTICAL_OFFSET_EXTRA*flowLabFraction,paint);
            }
        }

    }
}
