package com.freeme.scott.galleryui.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.freeme.scott.galleryui.design.R;

public class FreemeActionBarUpContainerLayout extends LinearLayout {
    private View mChildRightMenu;
    private View mChildHome;
    private View mChildTitle;

    public FreemeActionBarUpContainerLayout(Context context) {
        this(context, null);
    }

    public FreemeActionBarUpContainerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

//        if (FreemeContextUtil.isFreemeStyle(getContext())) {
            findChildViews();
            layoutAlignLeft(mChildHome, l, t, r, b);
            if (mChildTitle != null) {
                if (isLayoutRtl()) {
                    final int homeleft = (mChildHome == null || mChildHome.getVisibility() != View.VISIBLE) ?
                            r : mChildHome.getLeft();
                    if (homeleft > l + ((mChildTitle.getMeasuredWidth() + r) / 2)) {
                        layoutCenterHorizontal(mChildTitle, l, t, r, b);
                    } else {
                        layoutAlignLeft(mChildTitle, l, t, homeleft, b);
                    }
                } else {
                    final int homeRight = (mChildHome == null || mChildHome.getVisibility() != View.VISIBLE) ?
                            0 : mChildHome.getRight();
                    if (homeRight < l + ((r - mChildTitle.getMeasuredWidth()) / 2)) {
                        layoutCenterHorizontal(mChildTitle, l, t, r, b);
                        layoutAlignRight(mChildRightMenu, l, t, r, b);
                    } else {
                        layoutAlignLeft(mChildTitle, homeRight, t, r, b);
                        layoutAlignRight(mChildRightMenu, l, t, r, b);
                    }
                }
            }
//        }
    }

    private void findChildViews() {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getId() == R.id.freeme_title_layout) {
                mChildTitle = child;
            } else if (child.getId() == R.id.freeme_home_view) {
                mChildHome = child;
            } else if (child.getId() == R.id.freeme_actionbar_menuview) {
                mChildRightMenu = child;
            }
        }
    }

    private void layoutAlignLeft(View child, int l, int t, int r, int b) {
        if (child != null) {
            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();
            final MarginLayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
            final int left = isLayoutRtl() ? r - width - lp.leftMargin : l + lp.leftMargin;
            final int top = t + ((b - height) / 2);
            child.layout(left, top, left + width, top + height);
        }
    }

    private void layoutCenterHorizontal(View child, int l, int t, int r, int b) {
        if (child != null) {
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (width == 0 && height == 0) {
                child.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                width = child.getMeasuredWidth();
                height = child.getMeasuredHeight();
            }
            final int left = l + ((r - width) / 2);
            final int top = t + ((b - height) / 2);
            child.layout(left, top, left + width, top + height);
        }
    }

    private void layoutAlignRight(View child, int l, int t, int r, int b) {
        if (child != null) {
            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();
            final MarginLayoutParams lp = (LinearLayout.LayoutParams) child.getLayoutParams();
            final int right = isLayoutRtl() ? l + lp.rightMargin + width: r - lp.rightMargin;
            final int top = t + ((b - height) / 2);
            child.layout(right - width, top, right, top + height);
        }
    }

}
