package com.hj.backwordtool.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RadioGroup;

/**
 * Created by Administrator on 2014/8/14.
 */
public class HJRadioGroup extends RadioGroup {
    /**
     * HJRadioGroup 是否拦截事件
     */
    private boolean mHasEvent;

    public HJRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mHasEvent) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setHasEvent(boolean hasEvent) {
        mHasEvent = hasEvent;
    }
}
