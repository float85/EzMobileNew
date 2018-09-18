package com.fpts.mobile.eztrading.news;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ViewPagerAdapter;
import com.fpts.mobile.eztrading.databinding.FragmentHomeNewsBinding;
import com.fpts.mobile.eztrading.news.tablayout.TablayoutFragment;

import java.util.List;

public class HomeNewsFragment extends Fragment{
    private ViewPagerAdapter viewPagerAdapter;
    private List<NewsArticle> newsList;
    private FragmentHomeNewsBinding binding;

    public static HomeNewsFragment newInstance() {
        HomeNewsFragment fragment = new HomeNewsFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_news, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.news);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO: TamHV 6/28/2018
//        presenter.getData();
        //Check theme black
//        SharedPreferences shared = getActivity().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
//        boolean isLight = shared.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);
//        if (isLight) {
//            //Xet mau tab layout
//            binding.tabs.setTabTextColors(getResources().getColor(R.color.while_nhat), getResources().getColor(R.color.colorFont));
//        } else {
//            binding.tabs.setTabTextColors(getResources().getColor(R.color.while_nhat), getResources().getColor(R.color.colorFontDark));
//        }

        getDataSuccess();
    }

    @Override
    public void onResume() {
        super.onResume();
//        App.setPosition(Define.TYPE_MENU_NEWS);
//        App.setTypeFragment(Define.TYPE_MENU_NEWS);
//        MainActivity mainActivity = (MainActivity) getActivity();
//        mainActivity.setBack();
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(TablayoutFragment.newInstance(), getString(R.string.market_news));
        viewPagerAdapter.addFragment(TablayoutFragment.newInstance(), getString(R.string.department_report));
        viewPagerAdapter.addFragment(TablayoutFragment.newInstance(), getString(R.string.concern));
        viewPagerAdapter.addFragment(TablayoutFragment.newInstance(), getString(R.string.analysis_comment));
        viewPagerAdapter.addFragment(TablayoutFragment.newInstance(), getString(R.string.FPTS_news));
        viewPager.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        viewPager.setOffscreenPageLimit(5);
    }

    public void getDataSuccess() {
        Log.w("HomeNewsFragment", "getDataSuccess: ");
        setupViewPager(binding.viewPager);
    }
}
