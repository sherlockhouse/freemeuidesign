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

import com.freeme.scott.galleryui.design.GalleryPageAdapter;
import com.freeme.scott.galleryui.design.GalleryViewPager;
import com.freeme.scott.galleryui.design.ViewPagerTabs;

import java.util.ArrayList;

import static com.freeme.scott.galleryui.design.GalleryPageAdapter.TAB_COUNT_DEFAULT;
import static com.freeme.scott.galleryui.design.GalleryPageAdapter.TAB_INDEX_ALL_CONTACTS;
import static com.freeme.scott.galleryui.design.GalleryPageAdapter.TAB_INDEX_HISTORY;
import static com.freeme.scott.galleryui.design.GalleryPageAdapter.TAB_INDEX_SPEED_DIAL;

public class DynamicFragmentsDemoActivity extends
        LifecycleLoggingActivity implements ViewPager.OnPageChangeListener {


    private GalleryViewPager mViewPager;
    private ViewPagerTabs mViewPagerTabs;
    private GalleryPageAdapter mAdapter;
    private final ArrayList<ViewPager.OnPageChangeListener> mOnPageChangeListeners = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String[] tabTitles = new String[TAB_COUNT_DEFAULT];
        tabTitles[TAB_INDEX_SPEED_DIAL] = "test1";
        tabTitles[TAB_INDEX_HISTORY] = "test2";
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
}