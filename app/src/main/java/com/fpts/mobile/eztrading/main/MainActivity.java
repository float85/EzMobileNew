package com.fpts.mobile.eztrading.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ImageApp;
import com.fpts.mobile.eztrading.common.SettingApp;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.detailstock.DetailStockFragment;
import com.fpts.mobile.eztrading.events.EventsFragment;
import com.fpts.mobile.eztrading.home.HomeFragment;
import com.fpts.mobile.eztrading.mainmenu.DrawViewMenu;
import com.fpts.mobile.eztrading.marketOverviewDetail.MarketOverviewDetailFragment;
import com.fpts.mobile.eztrading.news.HomeNewsFragment;
import com.fpts.mobile.eztrading.newsDetail.NewsDetailFragment;
import com.fpts.mobile.eztrading.order.OrderMarPro;
import com.fpts.mobile.eztrading.watchlistSearch.CustomerAutoComleteTextView;
import com.fpts.mobile.eztrading.watchlistSearch.DataStock;
import com.fpts.mobile.eztrading.watchlistSearch.GetDataSearchStock;
import com.fpts.mobile.eztrading.watchlistdetail.WatchlistDetailFragment;
import com.fpts.mobile.eztrading.worldindexdetail.WorldIndexDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener, IMainMenuListener {
    Handler handler;
    LinearLayout linearLayout;
    Toolbar toolbar;
    private SettingApp settingApp;
    private DrawerLayout drawerLayout;
    private LinearLayout contentMenu;

    // TODO: TamHV 7/29/2018 9:10 PM search trên toolbar
    SearchView searchView;
    SearchView.SearchAutoComplete searchAutoComplete;
    ArrayList<CustomerAutoComleteTextView> listSearch = new ArrayList<>();
    String[] listCompany;

    protected int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    protected int heightItem = height / 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingApp = new SettingApp(this);

        linearLayout = findViewById(R.id.linear_layout);
        drawerLayout = findViewById(R.id.drawer_layout);
        contentMenu = findViewById(R.id.drawer_container);
        contentMenu.addView(new DrawViewMenu(this, this));
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(ImageApp.iconHomeMenu);
        toolbar.setBackgroundResource(R.drawable.bg_toolbar);

        setSupportActionBar(toolbar);
        drawerLayout.addDrawerListener(this);
//        setFragment(HomeFragment.newInstance());
        setFragment(OrderMarPro.newInstance());

        NavigationBottomMain navigationBottomMain = new NavigationBottomMain(this);
        LinearLayout linearLayout = findViewById(R.id.linear_layout_bottom);
        linearLayout.addView(navigationBottomMain.painNavigationBottom(this, heightItem));

        new Thread(() -> {
            ArrayList<String> list = GetDataSearchStock.getDataStock(MainActivity.this);
            ArrayList<CustomerAutoComleteTextView> arrayListStock = new ArrayList<>();
            for (int i = 0; i < list.size(); i = i + 4) {
                try {
                    arrayListStock.add(new CustomerAutoComleteTextView(list.get(i), list.get(i + 1), list.get(i + 2), list.get(i + 3)));
                } catch (Exception e) {

                }
            }

            listSearch = arrayListStock;
            DataStock.saveStock(this, listSearch);
            Log.d("MainActivity", listSearch.get(0).getName_en());

        }).start();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        handler = new Handler();
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        DataStock.saveStock(this, listSearch);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO: TamHV 6/19/2018
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search);

        // Get SearchView object.
        searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        // Get SearchView autocomplete object.
        searchAutoComplete = searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setTextColor(Color.BLACK);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.white);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.gray));
        searchAutoComplete.setThreshold(1);
//        searchAutoComplete.setFilters(presenter.validateEdittext());
        searchAutoComplete.setHint(R.string.hint_search_toolbar);
//        searchAutoComplete.setTypeface(typeface);

        searchAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AutoCompleteTextView AutoCompleteTextViewtest = searchView.findViewById(R.id.search_src_text);

                if (AutoCompleteTextViewtest.isPerformingCompletion()) {
                    // An item has been selected from the list. Ignore.
                    return;
                }
                if (String.valueOf(s).compareToIgnoreCase("") == 0) {
                    return;
                } else {
                    try {
                        searchAutoComplete = searchView.findViewById(R.id.search_src_text);
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < listSearch.size(); i++) {
                            String strStockCode = "";
                            String b = "vi";

                            if (b.equalsIgnoreCase("vi")) {
                                strStockCode = listSearch.get(i).getStock_code() + " - " + listSearch.get(i).getName_vn();
                            } else {
                                strStockCode = listSearch.get(i).getStock_code() + " - " + listSearch.get(i).getName_en();
                            }
                            if (listSearch.get(i).getStock_code().toLowerCase().startsWith(s.toString().toLowerCase())) {
                                list.add(strStockCode);
                            }
                        }
                        listCompany = new String[list.size()];
                        list.toArray(listCompany);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                R.layout.item_watchlist_search_recyclerview, listCompany);
                        searchAutoComplete.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchAutoComplete.setOnItemClickListener((parent, view, position, id) -> {
            // TODO: TamHV 6/24/2018 xử lí mã
            String[] strings = listCompany[position].split("\\s");
            searchAutoComplete.setText("");
            searchView.setIconified(true);
            setFragment(DetailStockFragment.newInstance(strings[0]));
            Toast.makeText(this, strings[0], Toast.LENGTH_SHORT).show();
        });

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            switch (ValueApp.vt) {
                case FROM_MAIN:
                    setFragment(HomeFragment.newInstance());
                    ValueApp.vt.equals(ValueApp.ENUM_VT.FROM_MAIN);
                    break;
                case FROM_MARKETOVERVIEW_DETAIL:
                    setFragment(MarketOverviewDetailFragment.newInstance());
                    break;
                case FROM_WATCHLIST_DETAIL:
                    setFragment(WatchlistDetailFragment.newInstance());
                    ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;
                    break;
                case FROM_WORLDINDEXES:
                    setFragment(WorldIndexDetailFragment.newInstance());
                    ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;
                    break;
                case FROM_EVENTS:
                    setFragment(EventsFragment.newInstance());
                    ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;
                    break;
                case FROM_NEWS:
                    setFragment(HomeNewsFragment.newInstance());
                    ValueApp.vt = ValueApp.ENUM_VT.FROM_MAIN;
                    break;
                case FROM_WATCHLIST_DETAIL_TRADING:
//                    setFragment();
                    break;
                default:
                    finish();
                    break;
            }
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linear_layout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                break;

        }
        return true;
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onItemClick(Fragment fragment) {
        drawerLayout.closeDrawer(Gravity.START);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linear_layout, fragment);
        transaction.commit();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null && (ev.getAction() != MotionEvent.ACTION_SCROLL && ev.getAction() != MotionEvent.ACTION_MOVE)
                && v instanceof EditText) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()) {
                EditText editText = (EditText) v;
                editText.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}