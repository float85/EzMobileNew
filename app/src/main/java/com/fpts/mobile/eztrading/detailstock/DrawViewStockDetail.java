package com.fpts.mobile.eztrading.detailstock;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ViewPagerAdapter;
import com.fpts.mobile.eztrading.customView.TabLayoutFont;
import com.fpts.mobile.eztrading.detailstock.analysis.FPTSAnalysisFragment;
import com.fpts.mobile.eztrading.detailstock.financial.FinancialFragment;
import com.fpts.mobile.eztrading.detailstock.financialfigures.FinancialFiguresFragment;
import com.fpts.mobile.eztrading.detailstock.foreignownership.ForeignOwnershipFragment;
import com.fpts.mobile.eztrading.detailstock.statistics.StatisticsFragment;
import com.fpts.mobile.eztrading.detailstock.trading.TradingFragment;

public class DrawViewStockDetail extends LinearLayout {
    private String TAG = getClass().getName();

    private int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int heightItem = height / 15;

    private Context context;
    private FragmentManager fragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String code = "";

    public DrawViewStockDetail(Context context, String code, FragmentManager fragmentManager) {
        super(context);
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.code = code;

        init();
    }

    private void init() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightItem);
        tabLayout = new TabLayoutFont(context);
        tabLayout.setId(ID.setID("tabLayout" + TAG));
        tabLayout.setSelectedTabIndicatorColor(ColorApp.colorGreen);
        tabLayout.setLayoutParams(params);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        viewPager = new ViewPager(context);
        viewPager.setId(ID.setID("viewPager" + TAG));

        this.setOrientation(VERTICAL);
        this.addView(tabLayout);
        this.addView(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
        adapter.addFragment(TradingFragment.newInstance(code), getContext().getString(R.string.watchlist_detail_trading));
        adapter.addFragment(StatisticsFragment.newInstance(code), getContext().getString(R.string.watchlist_detail_statistics));
        adapter.addFragment(ForeignOwnershipFragment.newInstance(code), getContext().getString(R.string.watchlist_detail_foreign_owner));
        adapter.addFragment(FPTSAnalysisFragment.newInstance(code), getContext().getString(R.string.watchlist_detail_analysis));
        adapter.addFragment(FinancialFragment.newInstance(code), getContext().getString(R.string.watchlist_detail_financial));
        adapter.addFragment(FinancialFiguresFragment.newInstance(code), getContext().getString(R.string.watchlist_financial_figures));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        adapter.notifyDataSetChanged();
    }
}
