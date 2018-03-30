package com.freeme.scott.galleryui.design.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by gulincheng on 18-3-27.
 */

public class GalleryPageAdapter extends PagerAdapter {

    private final String[] tabTitles;

    public static final int TAB_INDEX_HISTORY = 0;
    public static final int TAB_INDEX_ALL_CONTACTS = 1;
    public static final int TAB_INDEX_SPEED_DIAL = 2;
    //*/
    public static final int TAB_COUNT_DEFAULT = 3;

    public GalleryPageAdapter( String[] tabTitles) {
        this.tabTitles = tabTitles;
    }


    @Override
    public int getCount() {
        return TAB_COUNT_DEFAULT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return null;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    }

    @Override
    public int getItemPosition(Object object) {
        return 2;
    }

}
