package com.fpts.mobile.eztrading.chart;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.databinding.DialogChartCustomBinding;
import com.fpts.mobile.eztrading.main.MainActivity;
import com.fpts.mobile.eztrading.marketDetail.chart.HistoryChartOtherIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.fpts.mobile.eztrading.databinding.FragmentChartBinding;
import com.fpts.mobile.eztrading.watchlistSearch.CustomerAutoComleteTextView;
import com.fpts.mobile.eztrading.watchlistSearch.DataStock;
import com.fpts.mobile.eztrading.watchlistSearch.GetDataSearchStock;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.utils.Utils;

public class ChartFragment extends Fragment implements View.OnClickListener {
    private static final NavigableMap<Float, String> suffixes = new TreeMap<>();

    FragmentChartBinding binding;
    private Typeface typeface;
    private SharedPreferences preferences;
    private boolean isLight = true;

    String[] listchart = {DataChart.VOLUMN, DataChart.MACD, DataChart.RSI,
            DataChart.BOLINGER_BAND, DataChart.EMA, DataChart.STOCHASTICS};
    int a = 12, b = 26, c = 9;

    List<String> lg = new ArrayList<>();
    private String key = DataChart.VOLUMN;
    private int count = 5;
    List<String> list = new ArrayList<>();
    private DataChart pr;
    private ArrayList<HistoryChartOtherIndex> yVals1 = new ArrayList<>();

    private static final int TYPE_CHART_ONE_DAY = 0;
    private static final int TYPE_CHART_ONE_WEEK = 1;
    private static final int TYPE_CHART_ONE_MONTH = 2;
    private static final int TYPE_CHART_THREE_MONTH = 3;
    private static final int TYPE_CHART_SIX_MONTH = 4;
    private static final int TYPE_CHART_ONE_YEAR = 5;
    private static final int TYPE_CHART_TWO_YEAR = 6;
    private static final int TYPE_CHART_ALL = 7;

    AutoCompleteTextView autoCompleteTextViewtest1;
    ArrayList<CustomerAutoComleteTextView> listStock;
    String[] listCompany;

    // TODO: TamHV 8/9/2018 2:45 AM check
    private boolean check = false;
    public boolean refreshChart = false;

    public static ChartFragment newInstance() {
        ChartFragment fragment = new ChartFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ValueApp.contextChartFragment = getContext();
        ValueApp.activityChartFragment = getActivity();
    }

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chart, container, false);

        ValueApp.contextChart = getContext();
        ValueApp.activityChart = getActivity();

        typeface = ResourcesCompat.getFont(ValueApp.contextChart, R.font.free_sans);

        view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autoCompleteTextViewtest1 = view.findViewById(R.id.txtCode);

        pr = new DataChart(ValueApp.contextChart, yVals1, this);

        setAdapterSpinner();
        chartconfig();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.chart1);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");

        setAdapter();
        setAdapterSearchStock();
        chartconfig();
        binding.spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (listchart[i]) {
                    case DataChart.VOLUMN:
                        key = DataChart.VOLUMN;
                        pr.loadFilter(count, key, a, b, c);
                        binding.barChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.VISIBLE);
                        break;
                    case DataChart.MACD:
                        key = DataChart.MACD;
                        showDialog(key);
                        break;
                    case DataChart.RSI:
                        key = DataChart.RSI;
                        showDialog(key);
                        break;
                    case DataChart.BOLINGER_BAND:
                        key = DataChart.BOLINGER_BAND;
                        showDialog(key);
                        break;
                    case DataChart.EMA:
                        key = DataChart.EMA;
                        showDialog(key);
                        break;
                    case DataChart.STOCHASTICS:
                        key = DataChart.STOCHASTICS;
                        showDialog(key);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

//        binding.txtCode.setOnItemClickListener((parent, view1, position, id) -> {
//            String code = binding.txtCode.getText().toString();
//            code = code.substring(0, code.indexOf("-")).trim();
//
//            pr.loadDt(code, count, key, a, b, c);
//            binding.txtCode.setText("");
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.chart1 + code);
//            ((AppCompatActivity) getActivity()).getSupportActionBar()
//                    .setTitle(ValueApp.contextChart.getResources().getString(R.string.chart1) + " " + code);
////            hidekeyBoard();
//        });

        binding.txtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AutoCompleteTextView AutoCompleteTextViewtest = view.findViewById(R.id.txtCode);
                if (AutoCompleteTextViewtest.isPerformingCompletion()) {
                    return;
                }
                if (String.valueOf(charSequence).compareToIgnoreCase("") == 0) {
                    return;
                } else {
                    try {
                        autoCompleteTextViewtest1.setThreshold(1);
                        List<String> list = new ArrayList<>();
                        for (int j = 0; j < listStock.size(); j++) {
                            String strStockCode = listStock.get(j).getStock_code() + " - " + listStock.get(j).getName_vn();

                            if (listStock.get(j).getStock_code().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                                list.add(strStockCode);
                            }
                        }

                        listCompany = new String[list.size()];
                        list.toArray(listCompany);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ValueApp.contextChart.getApplicationContext(),
                                R.layout.item_watchlist_search_recyclerview, listCompany);
                        autoCompleteTextViewtest1.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        autoCompleteTextViewtest1.setOnItemClickListener((adapterView, view1, i, l) -> {
            String code = binding.txtCode.getText().toString();
            code = code.substring(0, code.indexOf("-")).trim();

            pr.loadDt(code, count, key, a, b, c);
            binding.txtCode.setText("");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.chart1 + listCompany[i]);
            ((AppCompatActivity) getActivity()).getSupportActionBar()
                    .setTitle(ValueApp.contextChart.getResources().getString(R.string.chart1) + " " + listCompany[i]);

        });

        getActivity().setTitle("Biểu đồ VNINDEX");
        selectTap(TYPE_CHART_ONE_WEEK);

        binding.tabOneWeek.setOnClickListener(this);
        binding.tabOneMonth.setOnClickListener(this);
        binding.tabThreeMonth.setOnClickListener(this);
        binding.tabSixMonth.setOnClickListener(this);
        binding.tabOneYear.setOnClickListener(this);
        binding.tabAll.setOnClickListener(this);
    }

//    private void hidekeyBoard() {
//        View view = getActivity().getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }

    private void setAdapterSpinner() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < listchart.length; i++) {
            list.add(listchart[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(ValueApp.contextChart, R.layout.item_chart_spinner_layout, list);
        adapter.setDropDownViewResource(R.layout.item_chart_spinner_layout);
        binding.spinnerType.setAdapter(adapter);
    }

    private void setAdapter() {
        for (int i = 0; i < listchart.length; i++) {
            list.add(listchart[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(ValueApp.contextChart, R.layout.item_chart_spinner_layout, list);
        adapter.setDropDownViewResource(R.layout.item_chart_spinner_layout);
        binding.spinnerType.setAdapter(adapter);
    }

    private void setAdapterSearchStock() {
        ArrayList<String> arrayListStock = DataStock.getCodeStock(ValueApp.contextChart);

        listStock = new ArrayList<>();
        for (int i = 0; i < arrayListStock.size(); i = i + 4) {
            try {
                listStock.add(new CustomerAutoComleteTextView(arrayListStock.get(i), arrayListStock.get(i + 1),
                        arrayListStock.get(i + 2), arrayListStock.get(i + 3)));
            } catch (Exception e) {

            }
        }

        ArrayAdapter<String> adapterStock = new ArrayAdapter(ValueApp.contextChart, R.layout.item_chart_spinner_layout, listStock);
        adapterStock.setDropDownViewResource(R.layout.item_chart_spinner_layout);
        binding.txtCode.setAdapter(adapterStock);
    }

    private void showDialog(final String key1) {
        final Dialog dialog = new Dialog(getContext());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        final DialogChartCustomBinding bd =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_chart_custom, null, false);
        dialog.setContentView(bd.getRoot());
        bd.tieude.setText(key1);
        switch (key1) {
            case DataChart.MACD:
                bd.name.setHint(R.string.chart_fast_avg_12);
                bd.name1.setHint(R.string.chart_slow_avg_26);
                bd.name2.setHint(R.string.chart_singal_avg_9);
                setEditTextMaxLength(bd.name2, 1);
                break;
            case DataChart.RSI:
                bd.name.setHint(R.string.chart_period_14);
                bd.name1.setVisibility(View.GONE);
                bd.name2.setVisibility(View.GONE);
                break;
            case DataChart.STOCHASTICS:
                bd.name.setHint(R.string.chart_period_14);
                bd.name1.setHint(R.string.chart_K_avg_3);
                bd.name2.setHint(R.string.chart_D_avg_3);
                bd.name1.setVisibility(View.VISIBLE);
                bd.name2.setVisibility(View.VISIBLE);
                setEditTextMaxLength(bd.name1, 1);
                setEditTextMaxLength(bd.name2, 1);
                break;
            case DataChart.BOLINGER_BAND:
                bd.name.setHint(R.string.chart_period_20);
                bd.name1.setHint(R.string.chart_deviations_2);
                bd.name1.setVisibility(View.VISIBLE);
                bd.name2.setVisibility(View.GONE);
                setEditTextMaxLength(bd.name1, 1);
                break;
            case DataChart.EMA:
                bd.name.setHint(R.string.chart_first_period_5);
                setEditTextMaxLength(bd.name, 1);
                bd.name1.setVisibility(View.GONE);
                bd.name2.setVisibility(View.GONE);
                break;
        }
        bd.save.setOnClickListener(v -> {
            switch (key1) {
                case DataChart.MACD:
                    if (bd.name.getText().toString().equals("")) a = 12;
                    else a = Integer.parseInt(bd.name.getText().toString());
                    if (bd.name1.getText().toString().equals("")) b = 26;
                    else b = Integer.parseInt(bd.name1.getText().toString());
                    if (bd.name2.getText().toString().equals("")) c = 9;
                    else c = Integer.parseInt(bd.name2.getText().toString());

                    if (a > 12 || b > 26 || c > 9) showMess();
                    else {
                        pr.loadFilter(count, key1, a, b, c);
                        binding.barChart.setVisibility(View.GONE);
                        binding.combineChart2.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                    break;
                case DataChart.RSI:
                    if (bd.name.getText().toString().equals("")) a = 14;
                    else a = Integer.parseInt(bd.name.getText().toString());

                    if (a > 14) showMess();
                    else {
                        pr.computing(key1, a, b, c, count);
//                            pr.loadFilter(count, key1, a, b, c);
                        binding.barChart.setVisibility(View.GONE);
                        binding.combineChart2.setVisibility(View.GONE);
                        binding.lineChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                    break;
                case DataChart.STOCHASTICS:
                    if (bd.name.getText().toString().equals(""))
                        a = 14;
                    else
                        a = Integer.parseInt(bd.name.getText().toString());
                    if (bd.name1.getText().toString().equals(""))
                        b = 3;
                    else
                        b = Integer.parseInt(bd.name1.getText().toString());
                    if (bd.name2.getText().toString().equals(""))
                        c = 3;
                    else
                        b = Integer.parseInt(bd.name2.getText().toString());
                    if (a > 14 || b > 3 || c > 3)
                        showMess();
                    else {
                        pr.computing(key1, a, b, c, count);
                        binding.barChart.setVisibility(View.GONE);
                        binding.combineChart2.setVisibility(View.GONE);
                        binding.lineChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.VISIBLE);

                        dialog.dismiss();
                    }
                    break;
                case DataChart.BOLINGER_BAND:
                    if (bd.name.getText().toString().equals("")) a = 20;
                    else a = Integer.parseInt(bd.name.getText().toString());
                    if (bd.name1.getText().toString().equals("")) b = 2;
                    else b = Integer.parseInt(bd.name1.getText().toString());
                    if (a > 20 || b > 2) showMess();
                    else {
                        pr.computing(key1, a, b, c, count);
                        binding.barChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.VISIBLE);
                        binding.combineChart2.setVisibility(View.GONE);
                        binding.lineChart.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                    break;

                case DataChart.EMA:
                    if (bd.name.getText().toString().equals("")) a = 5;
                    else a = Integer.parseInt(bd.name.getText().toString());

                    if (a > 5) showMess();
                    else {
                        pr.computing(key1, a, b, c, count);
                        binding.barChart.setVisibility(View.VISIBLE);
                        binding.combineChart1.setVisibility(View.VISIBLE);
                        binding.combineChart2.setVisibility(View.GONE);
                        binding.lineChart.setVisibility(View.GONE);
                        binding.candleStick.setVisibility(View.GONE);

                        dialog.dismiss();
                    }
                    break;
            }
        });
        bd.exit.setOnClickListener(v -> {
            key = DataChart.VOLUMN;
            binding.spinnerType.setSelection(0);
            dialog.dismiss();
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    private void setEditTextMaxLength(final EditText editText, int length) {
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(length);
        editText.setFilters(FilterArray);
    }

    private void showMess() {
        Toast.makeText(ValueApp.contextChart, getResources().getString(R.string.chart_error_message_values), Toast.LENGTH_SHORT).show();
    }

    // TODO:HoaDT 6/25/2018 One-week, One-month, Three-month, Six-Month, One-year, All
    private void selectTap(int position) {
        for (int i = 0; i < binding.menuTab.getChildCount(); i++) {
            TextView textview = (TextView) binding.menuTab.getChildAt(i);
            textview.setBackgroundResource(R.drawable.bg_home_tab_selector);
            textview.setTextColor(isLight ? ValueApp.contextChart.getResources().getColor(R.color.gray)
                    : ValueApp.contextChart.getResources().getColor(R.color.colorFontDark));
        }
        switch (position) {
            case TYPE_CHART_ONE_WEEK:
                pr.loadFilter(5, key, a, b, c);
                count = 5;
                binding.tabOneWeek.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabOneWeek.setTextColor(isLight ? ValueApp.contextChart.getResources().getColor(R.color.colorFont)
                        : ValueApp.contextChart.getResources().getColor(R.color.colorFontDark));
                break;
            case TYPE_CHART_ONE_MONTH:
                pr.loadFilter(21, key, a, b, c);
                count = 21;
                binding.tabOneMonth.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabOneMonth.setTextColor(isLight ? ValueApp.contextChart.getResources().getColor(R.color.colorFont)
                        : ValueApp.contextChart.getResources().getColor(R.color.colorFontDark));
                break;
            case TYPE_CHART_THREE_MONTH:
                pr.loadFilter(63, key, a, b, c);
                count = 63;
                binding.tabThreeMonth.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabThreeMonth.setTextColor(isLight ? ValueApp.contextChart.getResources().getColor(R.color.colorFont)
                        : ValueApp.contextChart.getResources().getColor(R.color.colorFontDark));
                break;
            case TYPE_CHART_SIX_MONTH:
                pr.loadFilter(126, key, a, b, c);
                count = 126;
                binding.tabSixMonth.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabSixMonth.setTextColor(isLight ? ValueApp.contextChart.getResources().getColor(R.color.colorFont)
                        : ValueApp.contextChart.getResources().getColor(R.color.colorFontDark));
                break;
            case TYPE_CHART_ONE_YEAR:
                pr.loadFilter(252, key, a, b, c);
                count = 252;
                binding.tabOneYear.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabOneYear.setTextColor(isLight ? ValueApp.contextChart.getResources().getColor(R.color.colorFont)
                        : ValueApp.contextChart.getResources().getColor(R.color.colorFontDark));
                break;
            case TYPE_CHART_ALL:
                pr.loadFilter(408, key, a, b, c);
                count = 408;
                binding.tabAll.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabAll.setTextColor(isLight ? ValueApp.contextChart.getResources().getColor(R.color.colorFont)
                        : ValueApp.contextChart.getResources().getColor(R.color.colorFontDark));
                break;
            default:
                binding.tabOneWeek.setBackgroundResource(R.drawable.bg_home_tab_selector_selected);
                binding.tabOneWeek.setTextColor(isLight ? ValueApp.contextChart.getResources().getColor(R.color.colorFont)
                        : ValueApp.contextChart.getResources().getColor(R.color.colorFontDark));
                break;
        }
    }

    private void chartconfig() {
        binding.barChart.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barChart.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barChart.setNoDataTextTypeface(typeface);
        binding.barChart.getAxisRight().setDrawGridLines(true);
        binding.barChart.setPinchZoom(false);

        binding.barChart.setBackgroundColor(isLight ? getResources().getColor(R.color.colorBackground) :
                getResources().getColor(R.color.colorBackgroundDark));
        binding.barChart.setDrawBarShadow(false);
        binding.barChart.setDrawValueAboveBar(true);
        binding.barChart.getDescription().setEnabled(false);
        binding.barChart.setMaxVisibleValueCount(10000);
        binding.barChart.setDrawGridBackground(false);
        binding.barChart.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barChart.setBorderColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.barChart.getLegend().setEnabled(false);
        binding.barChart.getXAxis().setTypeface(typeface);
        binding.barChart.getAxisLeft().setTypeface(typeface);
        binding.barChart.getAxisRight().setTypeface(typeface);

        binding.candleStick.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.candleStick.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.candleStick.setNoDataTextTypeface(typeface);
        binding.candleStick.getAxisRight().setDrawGridLines(true);
        binding.candleStick.setPinchZoom(false);

        binding.combineChart1.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.combineChart1.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.combineChart1.setNoDataTextTypeface(typeface);
        binding.combineChart1.getAxisRight().setDrawGridLines(true);
        binding.combineChart1.setPinchZoom(false);
        binding.combineChart1.getDescription().setEnabled(false);
        binding.combineChart1.setBackgroundColor(isLight ? getResources().getColor(R.color.white) :
                getResources().getColor(R.color.black));
        binding.combineChart1.setDrawGridBackground(false);
        binding.combineChart1.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE
        });
        binding.combineChart1.getXAxis().setTypeface(typeface);
        binding.combineChart1.getAxisLeft().setTypeface(typeface);
        binding.combineChart1.getAxisRight().setTypeface(typeface);

        binding.combineChart2.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.combineChart2.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.combineChart2.setNoDataTextTypeface(typeface);
        binding.combineChart2.getAxisRight().setDrawGridLines(true);
        binding.combineChart2.setPinchZoom(false);
        binding.combineChart2.getDescription().setEnabled(false);
        binding.combineChart2.setBackgroundColor(isLight ? getResources().getColor(R.color.white) :
                getResources().getColor(R.color.black));
        binding.combineChart2.setDrawGridBackground(false);

        binding.combineChart2.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });

        binding.lineChart.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.lineChart.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.lineChart.setNoDataTextTypeface(typeface);
        binding.lineChart.setDrawGridBackground(false);
        binding.lineChart.setBackgroundColor(isLight ? getResources().getColor(R.color.white) :
                getResources().getColor(R.color.black));
        binding.lineChart.getLegend().setEnabled(false);
        binding.lineChart.getDescription().setEnabled(false);
        binding.lineChart.setPinchZoom(false);
        binding.lineChart.getXAxis().setTypeface(typeface);
        binding.lineChart.getAxisLeft().setTypeface(typeface);
        binding.lineChart.getAxisRight().setTypeface(typeface);

        binding.candleStick.setNoDataTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.candleStick.getXAxis().setTextColor(isLight ? getResources().getColor(R.color.colorFont) :
                getResources().getColor(R.color.colorFontDark));
        binding.candleStick.setNoDataTextTypeface(typeface);
        binding.candleStick.setBackgroundColor(isLight ? getResources().getColor(R.color.colorBackground) :
                getResources().getColor(R.color.colorBackgroundDark));
        binding.candleStick.getDescription().setEnabled(false);
        binding.candleStick.setPinchZoom(false);
        binding.candleStick.setAutoScaleMinMaxEnabled(false);
        binding.candleStick.setDoubleTapToZoomEnabled(false);
        binding.candleStick.setDrawGridBackground(false);
        binding.candleStick.getXAxis().setTypeface(typeface);
        binding.candleStick.getAxisLeft().setTypeface(typeface);
        binding.candleStick.getAxisRight().setTypeface(typeface);
    }

    public void showChart1(ArrayList<HistoryChartOtherIndex> yVals1) {
        binding.candleStick.clear();
        binding.barChart.clear();
        binding.combineChart2.clear();
        binding.combineChart1.clear();
        binding.lineChart.clear();
        GetData.getCendel(ValueApp.contextChart, binding, yVals1, getDate(yVals1));
        GetData.getBarValue(ValueApp.contextChart, binding, yVals1, getDate(yVals1));
    }

    public void showChart2(ArrayList<HistoryChartOtherIndex> yVals1, List<MacdData> getMacd) {
        binding.candleStick.clear();
        binding.barChart.clear();
        binding.combineChart2.clear();
        binding.combineChart1.clear();
        binding.lineChart.clear();
        GetData.getCendel(ValueApp.contextChart, binding, yVals1, getDate(yVals1));
        GetData.getcombind(ValueApp.contextChart, binding, getMacd, getDate(yVals1));
    }

    public void showChart3(ArrayList<HistoryChartOtherIndex> yVals1, ArrayList<Double> sma,
                           ArrayList<Double> sma1, double[] listRsi, String key, int p1, int p3) {
        binding.candleStick.clear();
        binding.barChart.clear();
        binding.combineChart2.clear();
        binding.combineChart1.clear();
        binding.lineChart.clear();
        GetData.getCendel(ValueApp.contextChart, binding, yVals1, getDate(yVals1));
        GetData.showlineC(ValueApp.contextChart, binding, listRsi, sma, sma1, getDate(yVals1), p1, p3, key);
    }

    public void showChart4(ArrayList<Double> top, ArrayList<Double> mid, ArrayList<Double> bot,
                           ArrayList<Float> ema, ArrayList<HistoryChartOtherIndex> yVals1, String key) {
        binding.candleStick.clear();
        binding.barChart.clear();
        binding.combineChart2.clear();
        binding.combineChart1.clear();
        binding.lineChart.clear();
        GetData.getcombind1(ValueApp.contextChart, binding, yVals1, ema, top, mid, bot, getDate(yVals1), key);
        GetData.getBarValue(ValueApp.contextChart, binding, yVals1, getDate(yVals1));
    }

    private ArrayList<String> getDate(List<HistoryChartOtherIndex> yVals1) {
        final ArrayList<String> Date = new ArrayList<>();
        for (int i = 0; i < yVals1.size(); i++) {
            Date.add(yVals1.get(i).getCharTime());
        }
        return Date;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == binding.tabOneWeek.getId()) {
            selectTap(TYPE_CHART_ONE_WEEK);
        } else if (view.getId() == binding.tabOneMonth.getId()) {
            selectTap(TYPE_CHART_ONE_MONTH);
        } else if (view.getId() == binding.tabThreeMonth.getId()) {
            selectTap(TYPE_CHART_THREE_MONTH);
        } else if (view.getId() == binding.tabSixMonth.getId()) {
            selectTap(TYPE_CHART_SIX_MONTH);
        } else if (view.getId() == binding.tabOneYear.getId()) {
            selectTap(TYPE_CHART_ONE_YEAR);
        } else if (view.getId() == binding.tabAll.getId()) {
            selectTap(TYPE_CHART_ALL);
        }
    }
}
