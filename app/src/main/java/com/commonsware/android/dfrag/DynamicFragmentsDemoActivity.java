/***
 Copyright (c) 2012 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _The Busy Coder's Guide to Android Development_
 https://commonsware.com/Android
 */

package com.commonsware.android.dfrag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.freeme.scott.galleryui.design.adapter.GalleryPageAdapter;
import com.freeme.scott.galleryui.design.widget.FreemeBottomSelectedController;
import com.freeme.scott.galleryui.design.widget.FreemeBottomSelectedView;
import com.freeme.scott.galleryui.design.widget.GalleryViewPager;
import com.freeme.scott.galleryui.design.widget.ViewPagerTabs;

import java.util.ArrayList;

import static com.freeme.scott.galleryui.design.adapter.GalleryPageAdapter.TAB_COUNT_DEFAULT;
import static com.freeme.scott.galleryui.design.adapter.GalleryPageAdapter.TAB_INDEX_ALL_CONTACTS;
import static com.freeme.scott.galleryui.design.adapter.GalleryPageAdapter.TAB_INDEX_HISTORY;
import static com.freeme.scott.galleryui.design.adapter.GalleryPageAdapter.TAB_INDEX_SPEED_DIAL;

public class DynamicFragmentsDemoActivity extends
        LifecycleLoggingActivity implements ViewPager.OnPageChangeListener {


    private GalleryViewPager mViewPager;
    private ViewPagerTabs mViewPagerTabs;
    private GalleryPageAdapter mAdapter;
    private final ArrayList<ViewPager.OnPageChangeListener> mOnPageChangeListeners = new ArrayList<>();
    private static final int TAP_TEST = 0;
    private static final int BAR_TEST = 1;
    private static final int ACTIONMODE_TEST = 2;
    private FreemeBottomSelectedController mBottomSelectedController;

    private static final int ACTION_CODE_SHARE = 0x100;
    private static final int ACTION_CODE_DELETE = 0x200;
    private static final int[] mActionNames = new int[]{R.string.test,R.string.test};
    private static final int[] mActionCodes = new int[]{ACTION_CODE_SHARE, ACTION_CODE_DELETE};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int test = TAP_TEST;
        switch(test) {
            case TAP_TEST:
                setContentView(R.layout.main);
                String[] tabTitles = new String[TAB_COUNT_DEFAULT];
                tabTitles[TAB_INDEX_SPEED_DIAL] = "相册";
                tabTitles[TAB_INDEX_HISTORY] = "联系人";
                tabTitles[TAB_INDEX_ALL_CONTACTS] = "test3";
                mViewPager = findViewById(R.id.lists_pager);
                mAdapter = new GalleryPageAdapter(tabTitles);
                mViewPager.setAdapter(mAdapter);
                mViewPager.addOnPageChangeListener(this);
                mViewPagerTabs = findViewById(R.id.lists_pager_header);
        /*
        mViewPagerTabs.configureTabIcons(tabIcons);
        */
                mViewPagerTabs.setViewPager(mViewPager);
                addOnPageChangeListener(mViewPagerTabs);
                break;
            case BAR_TEST:
                setContentView(R.layout.bartest);
                TextView t = findViewById(R.id.freeme_actionbar_back_title);
                t.setText(android.R.string.cancel);
                TextView t2 = findViewById(R.id.freeme_actionbar_menuview_text1);
                t2.setText(android.R.string.selectAll);
                break;
            case ACTIONMODE_TEST:
                setContentView(R.layout.actionmenu);
                FreemeBottomSelectedView bottomContainer = findViewById(R.id.bottom_container);
                mBottomSelectedController = new FreemeBottomSelectedController(bottomContainer);
                FreemeBottomSelectedController controller = new FreemeBottomSelectedController(bottomContainer);
                if (true) {
                    controller.showActions(mActionNames, mActionCodes, mCallBack);
                } else {
                    controller.hideActions();
                }
                break;
            default:
                break;
        }

    }

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (!mOnPageChangeListeners.contains(onPageChangeListener)) {
            mOnPageChangeListeners.add(onPageChangeListener);
        }
    }

    public void showOther(View v) {
        startActivity(new Intent(this, OtherActivity.class));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        final int count = mOnPageChangeListeners.size();
        for (int i = 0; i < count; i++) {
            mOnPageChangeListeners.get(i).onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        final int count = mOnPageChangeListeners.size();
        for (int i = 0; i < count; i++) {
            mOnPageChangeListeners.get(i).onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        final int count = mOnPageChangeListeners.size();
        for (int i = 0; i < count; i++) {
            mOnPageChangeListeners.get(i).onPageScrollStateChanged(state);
        }
    }

    private FreemeBottomSelectedView.IFreemeBottomActionCallBack mCallBack
            = new  FreemeBottomSelectedView.IFreemeBottomActionCallBack(){
        @Override
        public void onAction(int actionCode) {
            switch (actionCode) {
                case ACTION_CODE_DELETE:

                    break;
                default:
                    break;
            }
        }
    };
}