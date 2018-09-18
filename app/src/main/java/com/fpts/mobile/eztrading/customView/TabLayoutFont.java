package com.fpts.mobile.eztrading.customView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;

public class TabLayoutFont extends TabLayout {
    private Typeface typeface;
    private Context context;

    public TabLayoutFont(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        if (typeface == null)
            typeface = ResourcesCompat.getFont(context, R.font.free_sans_bold);
    }

    @Override
    public void addTab(Tab tab, int position, boolean setSelected) {
        super.addTab(tab, position, setSelected);
        ViewGroup mainView = (ViewGroup) getChildAt(0);
        ViewGroup tabView = (ViewGroup) mainView.getChildAt(tab.getPosition());
        int tabChildCount = tabView.getChildCount();
        for (int i = 0; i < tabChildCount; i++) {
            View tabViewChild = tabView.getChildAt(i);
            if (tabViewChild instanceof TextView) {
                ((TextView) tabViewChild).setTypeface(typeface, Typeface.NORMAL);
            }
        }
    }
}
