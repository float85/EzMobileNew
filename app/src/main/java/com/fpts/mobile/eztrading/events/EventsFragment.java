package com.fpts.mobile.eztrading.events;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ViewPagerAdapter;
import com.fpts.mobile.eztrading.databinding.FragEventsBinding;
import com.fpts.mobile.eztrading.events.tablayout.EventsTablayoutFragment;

public class EventsFragment extends Fragment {
    FragEventsBinding binding;

    // TODO: TamHV 6/27/2018  Typeface
//    Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.free_sans);

    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_events, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.lich_su_kien);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");
        setupViewPager();

//        //set màu sáng tối cho tablayout
//        SharedPreferences preferences = getContext().getSharedPreferences(Define.SHARED_PREFRENCES_APP, Context.MODE_PRIVATE);
//        boolean isLight = preferences.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true);

//        if (isLight) {
//            binding.tabLayout.setTabTextColors(getResources().getColor(R.color.gray), getResources().getColor(R.color.colorFont));
//            binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.green));
//            binding.tabLayout.setBackgroundColor(getResources().getColor(R.color.colorBackground));
//            binding.viewpager.setBackgroundColor(getResources().getColor(R.color.colorBackground));
//
//        } else {
//            binding.tabLayout.setTabTextColors(getResources().getColor(R.color.gray), getResources().getColor(R.color.colorFontDark));
//            binding.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.green));
//            binding.tabLayout.setBackgroundResource(R.color.colorBackgroundDark);
//            binding.viewpager.setBackgroundResource(R.color.colorBackgroundDark);
//        }
    }

    public void setupViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(EventsTablayoutFragment.newInstance(false), getString(R.string.events_yesterday));
        viewPagerAdapter.addFragment(EventsTablayoutFragment.newInstance(false), getString(R.string.events_today));
        viewPagerAdapter.addFragment(EventsTablayoutFragment.newInstance(false), getString(R.string.events_tomorrow));
        viewPagerAdapter.addFragment(EventsTablayoutFragment.newInstance(true), getString(R.string.events_thisweek));
        viewPagerAdapter.addFragment(EventsTablayoutFragment.newInstance(true), getString(R.string.events_nextweek));
        binding.viewpager.setAdapter(viewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager);
        binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.viewpager.setOffscreenPageLimit(5);

    }
}
