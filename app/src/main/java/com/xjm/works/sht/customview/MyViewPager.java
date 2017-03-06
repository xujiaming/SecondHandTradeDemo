package com.xjm.works.sht.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/10/30 0030.
 */

public class MyViewPager extends ViewPager {

    private boolean scoll = true;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scoll)
            return false;
        else
            return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (!scoll)
            return false;
        else
            return super.onInterceptTouchEvent(arg0);
    }

    public boolean isScoll() {
        return scoll;
    }

    public void setScoll(boolean scoll) {
        this.scoll = scoll;
    }
}
