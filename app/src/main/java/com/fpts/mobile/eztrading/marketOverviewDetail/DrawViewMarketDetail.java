package com.fpts.mobile.eztrading.marketOverviewDetail;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ViewPagerAdapter;
import com.fpts.mobile.eztrading.customView.TabLayoutFont;
import com.fpts.mobile.eztrading.marketOverviewDetail.hnx.HNXFragment;
import com.fpts.mobile.eztrading.marketOverviewDetail.hsx.HSXFragment;

public class DrawViewMarketDetail extends LinearLayout {
    private Context context;

    private int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int heightItem = height / 15;

    private ViewPagerAdapter adapter;
    private FragmentManager fragmentManager;

    public  DrawViewMarketDetail(Context context, FragmentManager fragmentManager) {
        super(context);
        this.context = context;
        this.fragmentManager = fragmentManager;

        init();
    }

    private void init() {
        TabLayout tabLayout = tablayout();
        ViewPager viewPager = new ViewPager(context);
        viewPager.setId(100000000 + 1);

        this.setOrientation(VERTICAL);
        this.addView(tabLayout);
        this.addView(viewPager);

        adapter = new ViewPagerAdapter(fragmentManager);
        adapter.addFragment(HSXFragment.newInstance(), "HSX");
        adapter.addFragment(HNXFragment.newInstance(), "HNX");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        adapter.notifyDataSetChanged();
    }

    private TabLayout tablayout() {
        LayoutParams params =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightItem);

        TabLayout tabLayout = new TabLayoutFont(context);
        tabLayout.setSelectedTabIndicatorColor(ColorApp.colorGreen);
        tabLayout.setLayoutParams(params);

        return tabLayout;
    }
}
