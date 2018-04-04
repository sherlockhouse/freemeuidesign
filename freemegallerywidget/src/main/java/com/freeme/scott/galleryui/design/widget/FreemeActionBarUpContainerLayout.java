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
            MarginLayoutParams lp = (MarginLayoutParams) getLayoutParams();
            t = t - lp.topMargin + lp.bottomMargin;
            b = b - lp.topMargin + lp.bottomMargin;
            layoutAlignLeft(mChildHome, l, t, r, b);

            if (mChildTitle != null) {
                if (this.isLayoutRtl(this)) {
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
            final int left = isLayoutRtl(this) ? r - width - lp.leftMargin : l + lp.leftMargin;
            final int top = t + ((b - height) / 2) ;
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
            final int right = isLayoutRtl(this) ? l + lp.rightMargin + width: r - lp.rightMargin;
            final int top = t + ((b - height) / 2);
            child.layout(right - width, top, right, top + height);
        }
    }

    /**
     * Check to see if the current layout is Right-to-Left. This check is only
     * supported for API 17+. For earlier versions, this method will just return
     * false.
     *
     * NOTE: This is based on the private API method in {@link View} class.
     *
     * @return boolean Boolean indicating whether the currently locale is RTL.
     */
    public boolean isLayoutRtl(View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.LAYOUT_DIRECTION_RTL == view.getLayoutDirection();
        } else {
            return false;
        }
    }
}
