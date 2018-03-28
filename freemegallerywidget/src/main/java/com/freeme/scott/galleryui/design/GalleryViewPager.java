package com.freeme.scott.galleryui.design;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import android.support.v4.view.ViewPager;


/**
 * Created by gulincheng on 18-3-27.
 */

public class GalleryViewPager extends ViewPager {

    private boolean enableSwipingPages;

    public GalleryViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        enableSwipingPages = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (enableSwipingPages) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (enableSwipingPages) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    public void setEnableSwipingPages(boolean enabled) {
        enableSwipingPages = enabled;
    }
}
