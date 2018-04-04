package com.freeme.scott.galleryui.design.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freeme.scott.galleryui.design.R;

public class FreemeActionBarTitleLayout extends LinearLayout {
    public FreemeActionBarTitleLayout(Context context) {
        this(context, null);
    }

    public FreemeActionBarTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        setGravity(Gravity.CENTER_HORIZONTAL);

        final int padding = getResources().getDimensionPixelSize(
                R.dimen.actionbar_title_padding_freeme);
        setPadding(padding, 0, padding, 0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        TextView title = findViewById(R.id.action_bar_title);
        if (title != null) {
            title.setGravity(Gravity.CENTER);
        }
        TextView subtitle = findViewById(R.id.action_bar_subtitle);
        if (subtitle != null) {
            LayoutParams lp = (LayoutParams) subtitle.getLayoutParams();
            if (lp != null) {
                lp.topMargin = 0;
            }
        }
    }
}
