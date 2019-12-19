package com.zhaofan.customview_size_layout.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.zhaofan.customview_size_layout.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {
    private static final int PADDING = DisplayUtils.dpToPx(5);
    List<Rect> childrenBounds = new ArrayList<>();
    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthUsed = 0;
        int lineWidthUsed = 0;
        int heightUsed = 0;
        int lineHeight = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int horizenCount=0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if (widthMode!=MeasureSpec.UNSPECIFIED &&
            lineWidthUsed+childView.getMeasuredWidth() + horizenCount*PADDING >widthSize){
                lineWidthUsed = 0;
                horizenCount = 0;
                heightUsed += lineHeight;
                measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }
            horizenCount ++;
            Rect childrenBound;
            if (childrenBounds.size() <= i) {
                childrenBound = new Rect();
                childrenBounds.add(childrenBound);
            } else {
                childrenBound = childrenBounds.get(i);
            }
            childrenBound.set(lineWidthUsed, heightUsed, lineWidthUsed + childView.getMeasuredWidth()+PADDING, heightUsed + childView.getMeasuredHeight()+PADDING);
            lineWidthUsed += (childView.getMeasuredWidth()+horizenCount*PADDING);
            widthUsed = Math.max(lineWidthUsed,widthUsed);
            lineHeight = Math.max(lineHeight, childView.getMeasuredHeight()+PADDING);
        }
        int measureWidth = widthUsed;
        heightUsed += lineHeight;
        int measureHeight = heightUsed;
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            Rect childrenBound = childrenBounds.get(i);
            childView.layout(childrenBound.left+PADDING, childrenBound.top + PADDING, childrenBound.right, childrenBound.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attr) {
        return new MarginLayoutParams(getContext(), attr);
    }
}
