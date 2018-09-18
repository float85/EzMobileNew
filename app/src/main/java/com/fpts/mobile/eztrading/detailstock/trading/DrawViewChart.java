package com.fpts.mobile.eztrading.detailstock.trading;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class DrawViewChart extends ScrollView implements View.OnClickListener {

    private String TAG = getClass().getName();
    private int mHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int mWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int mHeightItem = mHeight / 15;
    private int mTextSize = mHeightItem * 3 / 10;

    private Typeface typeface;
    private Typeface typefaceBold;

    private Activity activity;
    private Context context;
    private LinearLayout linearLayout;
    private LinearLayout linearLayoutBarChart;
    private LinearLayout linearLayoutLineChart;
    private LinearLayout linearLayoutCandleStickChart;

    private ArrayList<String> arrayListChart = new ArrayList<>();
    private ArrayList<String> arrayListChartAll = new ArrayList<>();

    private BarChart barchart;
    private LineChart lineChart;
    private CandleStickChart candleStick;

    private static final NavigableMap<Float, String> suffixes = new TreeMap<>();

    static {
        suffixes.put((float) 1_000L, "K");
        suffixes.put((float) 1_000_000L, "M");
        suffixes.put((float) 1_000_000_000L, "G");
        suffixes.put((float) 1_000_000_000_000L, "T");
        suffixes.put((float) 1_000_000_000_000_000L, "P");
        suffixes.put((float) 1_000_000_000_000_000_000L, "E");
    }

    enum CHART_TIME {
        ONE_DAY,
        ONE_WEEK,
        ONE_MONTH,
        ONE_YEAR,
        FIVE_YEAR,
        MAX
    }

    public DrawViewChart(Context context, ArrayList<String> arrayListChart, ArrayList<String> arrayListChartAll) {
        super(context);
        this.context = context;
        this.activity = ValueApp.activityStockDetail;

        typeface = ResourcesCompat.getFont(context, R.font.free_sans);
        typefaceBold = ResourcesCompat.getFont(context, R.font.free_sans_bold);

        this.arrayListChart = arrayListChart;
        this.arrayListChartAll = arrayListChartAll;
        init();
    }

    private void init() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(8, 8, 8, 8);
        this.setLayoutParams(params);

        linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.addView(tablayout());
        linearLayout.addView(drawLineChart(arrayListChart));
        linearLayout.addView(mLine());
        linearLayout.addView(drawBarchart(arrayListChart));

        this.addView(linearLayout);
    }

    private void drawView(CHART_TIME chart_time) {
        int count = 0;
        switch (chart_time) {
            case ONE_DAY:
                linearLayout.removeViewAt(1);
                linearLayout.removeViewAt(1);
                linearLayout.removeViewAt(1);
                linearLayout.addView(drawLineChart(arrayListChart));
                linearLayout.addView(mLine());
                linearLayout.addView(drawBarchart(arrayListChart));
                return;
            case ONE_WEEK:
                count = 5 * 6;
                break;
            case ONE_MONTH:
                count = 21 * 6;
                break;
            case ONE_YEAR:
                count = 63 * 6;
                break;
            case FIVE_YEAR:
                count = 252 * 5 * 6;
                break;
            case MAX:
                count = arrayListChartAll.size();
                break;
            default:
                break;
        }
        ArrayList<String> array = new ArrayList<>();
        int numsize = 0;
        if (count > 0 && count < arrayListChartAll.size()) {
            numsize = arrayListChartAll.size() - count;
        }
        for (int i = numsize; i < arrayListChartAll.size(); i++) {
            array.add(arrayListChartAll.get(i));
        }

        linearLayout.removeViewAt(1);
        linearLayout.removeViewAt(1);
        linearLayout.removeViewAt(1);
        linearLayout.addView(drawCandleStickChart(array));
        linearLayout.addView(mLine());
        linearLayout.addView(drawBarchart(array));
    }

    private LinearLayout tablayout() {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(ID.setID("tablayout" + TAG));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);

        linearLayout.addView(textView("1D", true));
        linearLayout.addView(textView("1W", false));
        linearLayout.addView(textView("1M", false));
        linearLayout.addView(textView("1Y", false));
        linearLayout.addView(textView("5Y", false));
        linearLayout.addView(textView("MAX", false));

        return linearLayout;
    }

    private LinearLayout drawBarchart(ArrayList<String> array) {
        linearLayoutBarChart = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight / 4);
        params.topMargin = 8;
        linearLayoutBarChart.setLayoutParams(params);
        linearLayoutBarChart.setId(ID.setID("Barchart" + TAG));
        linearLayoutBarChart.setOrientation(LinearLayout.VERTICAL);
        linearLayoutBarChart.setGravity(Gravity.CENTER);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                barchart = new BarChart(context);
                barchart.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                linearLayoutBarChart.addView(barchart);
                barchart.setId(ID.setID("barchart" + TAG));
                barchart.getLegend().setEnabled(false);
                barchart.getDescription().setEnabled(false);
                barchart.getXAxis().setTextColor(ColorApp.colorText);
                barchart.getXAxis().setTypeface(typeface);
                barchart.getAxisLeft().setDrawLabels(false);
                barchart.getAxisLeft().setDrawGridLines(false);
                barchart.getAxisRight().setTypeface(typeface);
                barchart.setScaleEnabled(false);
                barchart.setDrawGridBackground(false);
                barchart.setDrawBarShadow(false);
                barchart.setNoDataTextTypeface(typeface);
                barchart.setNoDataTextColor(ColorApp.colorText);
                barchart.setBorderColor(ColorApp.colorBackground);


                final ArrayList<BarEntry> datachart = new ArrayList<>();
                final ArrayList<String> DateList = new ArrayList<>();

                for (int i = 0; i < array.size() / 6; i++) {
                    datachart.add(new BarEntry(i, Float.parseFloat(array.get(6 * i + 4))));
                    DateList.add(array.get(6 * i + 5));
                }
                IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(DateList);
                barchart.clear();
                XAxis xAxis = barchart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(false);
                xAxis.setValueFormatter(xAxisFormatter);
                xAxis.setLabelCount(4);
                xAxis.setTextColor(ColorApp.colorText);
                IAxisValueFormatter custom = new MyAxisValueFormatter();

                YAxis leftAxis = barchart.getAxisRight();
                leftAxis.setLabelCount(4, false);
                leftAxis.setDrawGridLines(true);
                leftAxis.setDrawAxisLine(true);
                leftAxis.setTextColor(ColorApp.colorText);
                leftAxis.setEnabled(true);
                leftAxis.setValueFormatter(custom);
                leftAxis.setAxisMinimum(2f); // start at zero

                BarDataSet set1;
                if (barchart.getData() != null &&
                        barchart.getData().getDataSetCount() > 0) {
                    set1 = (BarDataSet) barchart.getData().getDataSetByIndex(0);
                    set1.setValues(datachart);
                    barchart.getData().notifyDataChanged();
                    barchart.notifyDataSetChanged();
                } else {
                    set1 = new BarDataSet(datachart, "");
                    set1.setDrawIcons(false);
                    set1.setValueTextColor(ColorApp.colorText);
                    set1.setColors(ColorApp.colorGreen);
                    ArrayList<IBarDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1);
                    BarData data = new BarData(dataSets);
                    data.setValueTextSize(10f);
                    //data.setValueTypeface(mTfLight);
                    data.setBarWidth(0.9f);
                    barchart.setData(data);
                    for (IDataSet set : barchart.getData().getDataSets())
                        set.setDrawValues(!set.isDrawValuesEnabled());
                    barchart.invalidate();
                }
            }
        });

        return linearLayoutBarChart;
    }

    private LinearLayout drawLineChart(ArrayList<String> array) {
        linearLayoutLineChart = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight / 4);
        params.topMargin = 8;
        linearLayoutLineChart.setLayoutParams(params);
        linearLayoutLineChart.setId(ID.setID("LineChart" + TAG));
        linearLayoutLineChart.setOrientation(LinearLayout.VERTICAL);
        linearLayoutLineChart.setGravity(Gravity.CENTER);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lineChart = new LineChart(context);
                lineChart.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                lineChart.getLegend().setEnabled(false);
                lineChart.getDescription().setEnabled(false);
                lineChart.getXAxis().setTypeface(typeface);
                lineChart.getXAxis().setTextColor(ColorApp.colorText);
                lineChart.getAxisRight().setTypeface(typeface);
                lineChart.getAxisLeft().setDrawLabels(false);
                lineChart.getAxisLeft().setDrawGridLines(false);
                lineChart.setScaleEnabled(false);
                lineChart.setDrawGridBackground(false);
                lineChart.setNoDataTextTypeface(typeface);
                lineChart.setNoDataTextColor(ColorApp.colorText);

                final ArrayList<String> DateList = new ArrayList<>();
                final ArrayList<Entry> dataline = new ArrayList<>();
                for (int i = 0; i < array.size() / 6; i++) {
                    DateList.add(array.get(i * 6 + 5));
                }
                for (int i = 0; i < array.size() / 6; i++) {
                    dataline.add(new Entry(i, Float.parseFloat(array.get(i * 6 + 2)), null));
                }

                IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(DateList);
                XAxis xAxis2 = lineChart.getXAxis();
                xAxis2.setLabelCount(4);
                xAxis2.setTextColor(ColorApp.colorText);
                xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis2.setValueFormatter(xAxisFormatter);
                //Set Cột bên trái
                YAxis leftAxis2 = lineChart.getAxisRight();
                leftAxis2.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
                leftAxis2.setDrawGridLines(true);
                leftAxis2.setDrawAxisLine(true);
                leftAxis2.setDrawZeroLine(false);
                leftAxis2.setTextColor(ColorApp.colorText);
                // limit lines are drawn behind data (and not on top)
                leftAxis2.setDrawLimitLinesBehindData(true);

                LineDataSet lineDataSet;
                if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
                    lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
                    lineDataSet.setValues(dataline);
                    lineChart.getData().notifyDataChanged();
                    lineChart.notifyDataSetChanged();
                } else {
                    // create a dataset and give it a type
                    lineDataSet = new LineDataSet(dataline, "");
                    lineDataSet.setValueTextColor(ColorApp.colorText);
                    lineDataSet.setDrawIcons(false);
                    lineDataSet.setDrawCircles(false);
                    // set the line to be drawn like this "- - - - - -"
                    lineDataSet.setColor(ColorApp.colorText);
                    lineDataSet.setCircleColor(ColorApp.colorText);
                    lineDataSet.setLineWidth(0f);
                    lineDataSet.setCircleRadius(0f);
                    lineDataSet.setDrawCircleHole(false);
                    lineDataSet.setValueTextSize(0f);
                    lineDataSet.setDrawFilled(true);
                    lineDataSet.setFormLineWidth(0f);
                    lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                    lineDataSet.setFormSize(10f);

                    // TODO:HoaDT 7/10/2018 set background dưới đường line
                    lineDataSet.setFillColor(ColorApp.colorBackground);
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(lineDataSet); // add the datasets
                    // create a data object with the datasets
                    LineData data = new LineData(dataSets);
                    lineChart.setData(data);

                    // TODO:HoaDT 6/22/2018  ẩn cột kẻ dọc
                    lineChart.getXAxis().setDrawGridLines(false);
                    lineChart.getAxisRight().setDrawGridLines(true);
                }
                //dont forget to refresh the drawing
                lineChart.invalidate();
                //show values
                if (lineChart.getData() != null && lineChart.getData().getDataSets() != null) {
                    List<ILineDataSet> sets = lineChart.getData().getDataSets();
                    for (ILineDataSet iSet : sets) {
                        LineDataSet set = (LineDataSet) iSet;
                        set.setDrawValues(true);
                    }
                    lineChart.invalidate();
                }
                if (lineChart.getVisibleXRange() > 50) {
                    for (IDataSet set : lineChart.getData().getDataSets())
                        set.setDrawValues(false);
                }

                linearLayoutLineChart.addView(lineChart);
            }
        });
        return linearLayoutLineChart;
    }

    private LinearLayout drawCandleStickChart(ArrayList<String> array) {
        linearLayoutCandleStickChart = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight / 4);
        params.topMargin = 8;
        linearLayoutCandleStickChart.setId(ID.setID("CandleStickChart" + TAG));
        linearLayoutCandleStickChart.setLayoutParams(params);
        linearLayoutCandleStickChart.setOrientation(LinearLayout.VERTICAL);
        linearLayoutCandleStickChart.setGravity(Gravity.CENTER);

        activity.runOnUiThread(() -> {
            candleStick = new CandleStickChart(context);
            candleStick.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            candleStick.getLegend().setEnabled(false);
            candleStick.getDescription().setEnabled(false);
            candleStick.getXAxis().setTypeface(typeface);
            candleStick.getXAxis().setTextColor(ColorApp.colorText);
            candleStick.getAxisLeft().setDrawLabels(false);
            candleStick.getAxisLeft().setDrawGridLines(false);
            candleStick.getAxisRight().setTypeface(typeface);
            candleStick.setScaleEnabled(false);
            candleStick.setDrawGridBackground(false);
            candleStick.setNoDataTextTypeface(typeface);
            candleStick.setNoDataTextColor(ColorApp.colorText);

            final ArrayList<BarEntry> datachart = new ArrayList<>();
            final ArrayList<String> DateList = new ArrayList<>();
            final ArrayList<CandleEntry> chartcandle = new ArrayList<>();
            for (int i = 0; i < array.size() / 6; i++) {
                datachart.add(new BarEntry(i, Float.parseFloat(array.get(6 * i + 4))));
                DateList.add(array.get(i * 6 + 5));
            }
            for (int i = 0; i < array.size() / 6; i++) {
                chartcandle.add(new CandleEntry(
                        i,
                        Float.parseFloat(array.get(i * 6 + 2)),
                        Float.parseFloat(array.get(i * 6 + 3)),
                        Float.parseFloat(array.get(i * 6 + 0)),
                        Float.parseFloat(array.get(i * 6 + 1)),
                        null));
            }
            IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(DateList);
            XAxis xAxis1 = candleStick.getXAxis();
            xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis1.setDrawGridLines(false);
            xAxis1.setValueFormatter(xAxisFormatter);
            xAxis1.setTextColor(ColorApp.colorText);
            xAxis1.setGranularity(1f);
            xAxis1.setLabelCount(4);

            YAxis leftAxis1 = candleStick.getAxisRight();
            leftAxis1.setLabelCount(4, false);
            leftAxis1.setDrawGridLines(true);
            leftAxis1.setTextColor(ColorApp.colorText);
            leftAxis1.setEnabled(true);
            leftAxis1.setDrawAxisLine(true);
            leftAxis1.setAxisLineColor(getResources().getColor(R.color.black));
            if (datachart != null && datachart.size() > 0) {
                CandleDataSet set12 = new CandleDataSet(chartcandle, "Data Set");

                set12.setDrawIcons(false);
                set12.setAxisDependency(YAxis.AxisDependency.LEFT);
                set12.setShadowWidth(0.7f);
                set12.setDecreasingColor(ColorApp.colorRed);
                set12.setDecreasingPaintStyle(Paint.Style.FILL);
                set12.setIncreasingColor(ColorApp.colorGreen);
                set12.setNeutralColor(ColorApp.colorBlue);
                set12.setIncreasingPaintStyle(Paint.Style.FILL);

                set12.setValueTextColor(ColorApp.colorText);

                candleStick.setData(new CandleData(set12));
                candleStick.invalidate();
                for (IDataSet set : candleStick.getData().getDataSets())
                    set.setDrawValues(false);

                candleStick.invalidate();
            }
            linearLayoutCandleStickChart.addView(candleStick);
        });
        return linearLayoutCandleStickChart;
    }

    private TextView textView(String str, boolean isSelected) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem * 6 / 10);
        params.weight = 1;
        textView.setId(ID.setID(str + TAG));
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(pxToDp(mTextSize));
        textView.setTypeface(isSelected ? typefaceBold : typeface);
        textView.setBackgroundResource(isSelected ? R.drawable.bg_item_chart_tab_select : R.drawable.bg_item_chart_tab);
        textView.setTextColor(ColorApp.colorText);
        textView.setAllCaps(false);
        textView.setMaxLines(1);
        textView.setSingleLine();
        textView.setText(str);

        textView.setOnClickListener(this);
        return textView;
    }

    private View mLine() {
        View view = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(8, 20, 8, 20);
        view.setLayoutParams(params);
        view.setBackgroundColor(ColorApp.colorBackgroundDivider);

        return view;
    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == ID.getID("1D" + TAG)) {

            findViewById(ID.getID("1D" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab_select);
            findViewById(ID.getID("1W" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1M" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("5Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("MAX" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            ((TextView) findViewById(ID.getID("1D" + TAG))).setTypeface(typefaceBold);
            ((TextView) findViewById(ID.getID("1W" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1M" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("5Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("MAX" + TAG))).setTypeface(typeface);

            drawView(CHART_TIME.ONE_DAY);

        } else if (id == ID.getID("1W" + TAG)) {
            findViewById(ID.getID("1D" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1W" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab_select);
            findViewById(ID.getID("1M" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("5Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("MAX" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            ((TextView) findViewById(ID.getID("1D" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1W" + TAG))).setTypeface(typefaceBold);
            ((TextView) findViewById(ID.getID("1M" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("5Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("MAX" + TAG))).setTypeface(typeface);

            drawView(CHART_TIME.ONE_WEEK);

        } else if (id == ID.getID("1M" + TAG)) {
            findViewById(ID.getID("1D" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1W" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1M" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab_select);
            findViewById(ID.getID("1Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("5Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("MAX" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);

            ((TextView) findViewById(ID.getID("1D" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1W" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1M" + TAG))).setTypeface(typefaceBold);
            ((TextView) findViewById(ID.getID("1Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("5Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("MAX" + TAG))).setTypeface(typeface);

            drawView(CHART_TIME.ONE_MONTH);

        } else if (id == ID.getID("1Y" + TAG)) {
            findViewById(ID.getID("1D" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1W" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1M" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab_select);
            findViewById(ID.getID("5Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("MAX" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);

            ((TextView) findViewById(ID.getID("1D" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1W" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1M" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1Y" + TAG))).setTypeface(typefaceBold);
            ((TextView) findViewById(ID.getID("5Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("MAX" + TAG))).setTypeface(typeface);

            drawView(CHART_TIME.ONE_YEAR);

        } else if (id == ID.getID("5Y" + TAG)) {
            findViewById(ID.getID("1D" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1W" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1M" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("5Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab_select);
            findViewById(ID.getID("MAX" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);

            ((TextView) findViewById(ID.getID("1D" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1W" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1M" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("5Y" + TAG))).setTypeface(typefaceBold);
            ((TextView) findViewById(ID.getID("MAX" + TAG))).setTypeface(typeface);

            drawView(CHART_TIME.FIVE_YEAR);

        } else if (id == ID.getID("MAX" + TAG)) {
            findViewById(ID.getID("1D" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1W" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1M" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("1Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("5Y" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab);
            findViewById(ID.getID("MAX" + TAG)).setBackgroundResource(R.drawable.bg_item_chart_tab_select);

            ((TextView) findViewById(ID.getID("1D" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1W" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1M" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("1Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("5Y" + TAG))).setTypeface(typeface);
            ((TextView) findViewById(ID.getID("MAX" + TAG))).setTypeface(typefaceBold);

            drawView(CHART_TIME.MAX);

        }
    }


    private class DayAxisValueFormatter implements IAxisValueFormatter {

        private ArrayList<String> Date;

        public DayAxisValueFormatter(ArrayList<String> Date) {
            this.Date = Date;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            if (value >= Date.size() || value < 0) return "";
            else
                return Date.get((int) value);
        }
    }

    private class MyAxisValueFormatter implements IAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyAxisValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0.0");
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return format(value);
        }
    }

    // TODO:HoaDT 6/26/2018 Cập nhật cột giá trị K, G, M, T, P, E
    public static String format(float value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Float.toString(value); //deal with easy case

        Map.Entry<Float, String> e = suffixes.floorEntry(value);
        Float divideBy = e.getKey();
        String suffix = e.getValue();

        float truncated = Math.round(value / (divideBy / 10) * 1000) / 1000; //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

}
