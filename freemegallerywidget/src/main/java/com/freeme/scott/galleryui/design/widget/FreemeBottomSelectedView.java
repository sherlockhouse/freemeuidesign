package com.freeme.scott.galleryui.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freeme.scott.galleryui.design.R;


public class FreemeBottomSelectedView extends LinearLayout {

    public interface IFreemeBottomActionCallBack {
        void onAction(int actionCode);
    }

    public FreemeBottomSelectedView(Context context) {
        this(context, null);
    }

    public FreemeBottomSelectedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FreemeBottomSelectedView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public FreemeBottomSelectedView(Context context, AttributeSet attrs, int defStyleAttr,
                                    int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        isInit = true;
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(getResources().getColor(
                R.color.freeme_bottom_menu_container_bg_color));

        mTypedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                mTypedValue, true);

        mParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mParams.weight = 1;

        removeAllViews();
        addDividerLine();
        addMenuContainer();
    }

    private void addDividerLine() {
        ImageView divider = new ImageView(getContext());
        divider.setBackgroundColor(getResources().getColor(
                R.color.freeme_bottom_menu_container_divider_color));
        addView(divider, new LayoutParams(LayoutParams.MATCH_PARENT, 1));
    }

    private void addMenuContainer() {
        mContainer = new LinearLayout(getContext());
        mContainer.setOrientation(LinearLayout.HORIZONTAL);
        int height = getResources().getDimensionPixelSize(
                R.dimen.freeme_bottom_menu_container_height);
        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, height));
    }

    private boolean isInit = false;
    private IFreemeBottomActionCallBack mCallBack;
    private TypedValue mTypedValue;
    private LayoutParams mParams;
    private LinearLayout mContainer;

    public void setFreemeBottomActionCallBack(IFreemeBottomActionCallBack callBack) {
        this.mCallBack = callBack;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addActions(String[] actionNames, int[] actionCodes) {
        if (actionNames == null || actionCodes == null
                || actionNames.length != actionCodes.length) {
            return;
        }

        if (!isInit) {
            init();
        }
        mContainer.removeAllViews();

        for (int i = 0; i < actionNames.length; i++) {
            addAction(actionNames[i], actionCodes[i]);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addActions(int[] actionNames, int[] actionCodes) {
        if (actionNames == null || actionCodes == null
                || actionNames.length != actionCodes.length) {
            return;
        }

        if (!isInit) {
            init();
        }
        mContainer.removeAllViews();

        for (int i = 0; i < actionNames.length; i++) {
            addAction(actionNames[i], actionCodes[i]);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addAction(String name, int code) {
        TextView tv = getActionText(code);
        tv.setText(name);
        mContainer.addView(tv);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addAction(int name, final int code) {
        TextView tv = getActionText(code);
        tv.setText(name);
        mContainer.addView(tv);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private TextView getActionText(int code) {
        TextView tv = new TextView(getContext());
        tv.setId(code);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(new ColorStateList(
                new int[][]{{android.R.attr.state_enabled}, {-android.R.attr.state_enabled}},
                new int[]{getContext().getColor(R.color.freeme_color_accent),
                        getContext().getColor(R.color.freeme_color_accent_disable)}));
        tv.setTextSize(15);
        tv.setEnabled(false);
        tv.setClickable(false);
        if (mParams != null) {
            tv.setLayoutParams(mParams);
        }
        if (mTypedValue != null) {
            tv.setBackgroundResource(mTypedValue.resourceId);
        }
        final int mycode = code;

        tv.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mCallBack != null) {
                    mCallBack.onAction(mycode);
                }
            }
        });
        return tv;
    }

    public void showAction(boolean show) {
        setVisibility(show ? VISIBLE : GONE);
        if (!show && mContainer != null) {
            mContainer.removeAllViews();
        }
    }

    public void updateActionName(String actionName, int actionCode) {
        TextView tv = findViewById(actionCode);
        if (tv != null) {
            tv.setText(actionName);
        }
    }

    public void updateActionName(int actionName, int actionCode) {
        TextView tv = findViewById(actionCode);
        if (tv != null) {
            tv.setText(actionName);
        }
    }

    public void updateActionEnabled(boolean enabled, int actionCode) {
        TextView tv = findViewById(actionCode);
        if (tv != null) {
            tv.setEnabled(enabled);
            tv.setClickable(enabled);
        }
    }
}
