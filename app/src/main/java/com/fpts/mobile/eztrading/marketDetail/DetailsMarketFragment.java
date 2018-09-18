package com.fpts.mobile.eztrading.marketDetail;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.marketDetail.chart.ChartFragment;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeHNX;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeHNX30;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeItemVNI;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeUpcom;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeVNI;
import com.fpts.mobile.eztrading.marketDetail.data.DetailHomeVNI30;
import com.fpts.mobile.eztrading.marketDetail.tablayout.TablayoutFragment;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import com.fpts.mobile.eztrading.databinding.FragDetailsHomeBinding;

import static android.widget.LinearLayout.HORIZONTAL;

public class DetailsMarketFragment extends Fragment {
    FragDetailsHomeBinding binding;
    String sIndex;
    String marketName;
    String sLink;
    View view;

    LinearLayout linearLayoutAll;

    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels / 4;
    protected int height = width;
    protected int heightItem = height / 4;

//    protected String colorNhay = "#5a5a5a";
//    protected String colorMD = "#ffffff";

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket(DataMarketDetail.getLinkSocket());
        } catch (URISyntaxException e) {
        }
    }

    ArrayList<Emitter.Listener> ab1 = new ArrayList<>();

    public static DetailsMarketFragment newInstance(String idURL) {
        DetailsMarketFragment fragment = new DetailsMarketFragment();

        Bundle bundle = new Bundle();
        bundle.putString("idURL", idURL);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_details_home, container, false);
//        view = inflater.inflate(R.layout.frag_details_home, container, false);
//        App.setPosition(Define.TYPE_MENU_DETAIL_PAGE);
        view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            marketName = getArguments().getString("idURL").toLowerCase();

            linearLayoutAll = view.findViewById(R.id.lnS1S2S3);

            if (marketName.equalsIgnoreCase("hnx")) {
                sIndex = "HNX";
                sLink = "realtime_index_ha";
            } else if (marketName.equalsIgnoreCase("vni")) {
                sIndex = "VNI";
                sLink = "realtime_index_ho";
            } else if (marketName.equalsIgnoreCase("upcom")) {
                sIndex = "UPCOM";
                sLink = "realtime_index_up";
            } else if (marketName.equalsIgnoreCase("vn30")) {
                sIndex = "VN30";
                sLink = "realtime_index_vni30";
            } else if (marketName.equalsIgnoreCase("hnx30")) {
                sIndex = "HNX30";
                sLink = "realtime_index_hnx30";
            } else {
                marketName = "vni";
                sIndex = "VNI";
                sLink = "realtime_index_ho";
            }
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(sIndex);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");

            ValueApp.activityMarketDetail = getActivity();
            ValueApp.contextMarketDetail = getContext();
        }

        setupViewPager();

        new Thread(() -> {
            if (marketName.equalsIgnoreCase("hnx")) {
                ArrayList<DetailHomeHNX> arrayList = GetJsonMarketDetail.getHaData(ValueApp.contextMarketDetail, sLink);
                for (int i = 0; i < arrayList.size(); i++) {
                    int finalI = i;
                    ValueApp.activityMarketDetail.runOnUiThread(() -> {
                        loadDataHNX(arrayList.get(finalI));
                    });
                    Log.d("thunghiem", arrayList.get(i).getDate());
                }
            } else if (marketName.equalsIgnoreCase("upcom")) {
                ArrayList<DetailHomeUpcom> arrayList = GetJsonMarketDetail.getUpData(ValueApp.contextMarketDetail, sLink);
                for (int i = 0; i < arrayList.size(); i++) {
                    int finalI = i;
                    ValueApp.activityMarketDetail.runOnUiThread(() -> {
                        loadDataUPCOM(arrayList.get(finalI));
                    });
                    Log.d("thunghiem", arrayList.get(i).getDate());
                }
            } else if (marketName.equalsIgnoreCase("vn30")) {
                ArrayList<DetailHomeVNI30> arrayList = GetJsonMarketDetail.getvni30Data(ValueApp.contextMarketDetail, sLink);
                for (int i = 0; i < arrayList.size(); i++) {
                    int finalI = i;
                    ValueApp.activityMarketDetail.runOnUiThread(() -> {
                        loadDataVNI30(arrayList.get(finalI));
                    });
                    Log.d("thunghiem", arrayList.get(i).getDate());
                }
            } else if (marketName.equalsIgnoreCase("hnx30")) {
                ArrayList<DetailHomeHNX30> arrayList = GetJsonMarketDetail.getHnx30Data(ValueApp.contextMarketDetail, sLink);
                for (int i = 0; i < arrayList.size(); i++) {
                    int finalI = i;
                    ValueApp.activityMarketDetail.runOnUiThread(() -> {
                        loadDataHNX_30(arrayList.get(finalI));
                    });
                    Log.d("thunghiem", arrayList.get(i).getDate());
                }
            } else {
                ArrayList<DetailHomeVNI> arrayList = GetJsonMarketDetail.getHoData(ValueApp.contextMarketDetail, sLink);
                for (int i = 0; i < arrayList.size(); i++) {
                    int finalI = i;
                    ValueApp.activityMarketDetail.runOnUiThread(() -> {
                        loadDataVNI(arrayList.get(finalI));
                    });
                    Log.d("thunghiem", arrayList.get(i).getDate());
                }

                ArrayList<DetailHomeItemVNI> arrayList1 = GetJsonMarketDetail.getItemHoData(ValueApp.contextMarketDetail, sLink);
                for (int i = 0; i < arrayList1.size(); i++) {
                    int finalI = i;
                    ValueApp.activityMarketDetail.runOnUiThread(() -> loadDataItemVNI(arrayList1.get(finalI), "phiên " + String.valueOf(finalI + 1)));
                    Log.d("thunghiem", arrayList1.get(i).getCHGINDEX());
                }
            }

        }).start();

        new reTime().execute();

//        if (!DataMarketDetail.isConnected(getContext())) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////            builder.setTitle(getResources().getString(R.string.dialog_error));
//            builder.setMessage(getResources().getString(R.string.dialog_error));
//            builder.setCancelable(false);
////            builder.setPositiveButton("", (dialogInterface, i) -> {
////            });
//            builder.setNegativeButton(getResources().getString(R.string.dialog_content) + " =>>>", (dialogInterface, i) -> {
//                dialogInterface.dismiss();
//                getFragmentManager().popBackStack();
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//        }

//        if (sIndex.equalsIgnoreCase("VNI")) {
//            binding.lnS1S2S3.setVisibility(View.VISIBLE);
//        } else {
//            binding.lnS1S2S3.setVisibility(View.GONE);
//        }

        binding.lnIndexOverview.setBackgroundColor(ColorApp.colorBackgroundHeaderSub);
        binding.txtUpDownTitle.setTextColor(ColorApp.colorText);
        binding.txtPutthroughTitle.setTextColor(ColorApp.colorText);

        binding.txtValue.setTextColor(ColorApp.colorText);
        binding.txtVolume.setTextColor(ColorApp.colorText);

        binding.txtPutVal.setTextColor(ColorApp.colorText);
        binding.txtPutVol.setTextColor(ColorApp.colorText);

        //tab layout
        binding.tablayout.setTabTextColors(ColorApp.colorTextTablelayout, ColorApp.colorBackgroundTablelayoutSelect);
        binding.tablayout.setSelectedTabIndicatorColor(ColorApp.colorTextTablelayoutSelected);
        binding.tablayout.setBackgroundColor(ColorApp.colorBackgroundTablelayout);
        binding.viewpager.setBackgroundColor(ColorApp.colorBackgroundHeaderSub);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        App.setPosition(Define.TYPE_MENU_DETAIL_PAGE);
//        App.setTypeFragment(Define.TYPE_MENU_DETAIL_PAGE);
//        MainActivity mainActivity = (MainActivity) getActivity();
//        mainActivity.setBack();
//    }

    public void setupViewPager() {
        FragmentManager manager = getChildFragmentManager();
        ViewPagerAdapter adapterTabLayout = new ViewPagerAdapter(manager);

        adapterTabLayout.addFragment(ChartFragment.newInstance(marketName), getResources().getString(R.string.detailhome_tablayout_chart));
        adapterTabLayout.addFragment(TablayoutFragment.newInstance(sIndex, "1"), getResources().getString(R.string.detailhome_tablayout_topgainers));
        adapterTabLayout.addFragment(TablayoutFragment.newInstance(sIndex, "2"), getResources().getString(R.string.detailhome_tablayout_toplosers));
        adapterTabLayout.addFragment(TablayoutFragment.newInstance(sIndex, "3"), getResources().getString(R.string.detailhome_tablayout_mostactive));

        binding.viewpager.setAdapter(adapterTabLayout);
        binding.viewpager.setOffscreenPageLimit(4);
        binding.tablayout.setupWithViewPager(binding.viewpager);
        binding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tablayout));
        binding.tablayout.setTabsFromPagerAdapter(adapterTabLayout);

        setCustomFont();
    }

    public void setCustomFont() {

        ViewGroup vg = (ViewGroup) binding.tablayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    Typeface typeface = ResourcesCompat.getFont(ValueApp.contextMarketDetail, R.font.free_sans_bold);
                    ((TextView) tabViewChild).setTypeface(typeface, Typeface.NORMAL);
                    ((TextView) tabViewChild).setTextSize(R.dimen._11sdp);
                }
            }
        }
    }

    public void loadDataVNI(DetailHomeVNI detailHomeVNI) {
        try {
            binding.txtIndex.setText(sIndex + ": " + detailHomeVNI.getHOMARKETINDEX());
        } catch (Exception e) {
            binding.txtIndex.setText(sIndex + ": " + "0");
        }

        try {
            binding.txtVolume.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                    R.string.marketOverview_detail_txtS1Qty, detailHomeVNI.getHOTOTALQTTY())));
        } catch (Exception e) {
            binding.txtVolume.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                    R.string.marketOverview_detail_txtS1Qty, "0")));
        }

        try {
            binding.txtChange.setText(Html.fromHtml(
                    ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChange, detailHomeVNI.getHOCHGINDEX())));
        } catch (Exception e) {
            binding.txtChange.setText(Html.fromHtml(
                    ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChange, "0")));
        }

        try {
            binding.txtChangePrecent.setText(Html.fromHtml(
                    ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChangePrecent, detailHomeVNI.getHOPCTINDEX())));
        } catch (Exception e) {
            binding.txtChangePrecent.setText(Html.fromHtml(
                    ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChangePrecent, "0")));
        }

        try {
            binding.txtValue.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                    R.string.marketOverview_detail_txtS1Val, detailHomeVNI.getHOTOTALVALUE())));
        } catch (Exception e) {
            binding.txtValue.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                    R.string.marketOverview_detail_txtS1Val, "0")));
        }

        try {
            binding.txtUp.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeVNI.getHOADVANCES())));
            binding.txtBt.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeVNI.getHONOCHANGE())));
            binding.txtDown.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeVNI.getHODECLINES())));
        } catch (Exception e) {
        }

        try {
            binding.txtPutVol.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVol, detailHomeVNI.getHOPTTOTALQTTY())));
        } catch (Exception e) {
            binding.txtPutVol.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVol, "0")));
        }

        try {
            binding.txtPutVal.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVal, detailHomeVNI.getHOPTTOTALVALUE())));
        } catch (Exception e) {
            binding.txtPutVal.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVal, "0")));
        }

        try {
            setColorUpDown(detailHomeVNI.getStrArrow(), binding.txtIndex);
            setColorUpDown(detailHomeVNI.getStrArrow(), binding.txtChange);
            setColorUpDown(detailHomeVNI.getStrArrow(), binding.txtChangePrecent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDataItemVNI(DetailHomeItemVNI detailHome, String sPhien) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(ValueApp.contextMarketDetail);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        linearLayout.addView(textView5(sPhien));

        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 4f);
        LinearLayout linearLayout1 = new LinearLayout(ValueApp.contextMarketDetail);
        linearLayout1.setLayoutParams(layoutParams1);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayout1.setGravity(Gravity.CENTER);

        TextView txtS1Index = new TextView(ValueApp.contextMarketDetail);
        txtS1Index.setText(detailHome.getMARKETINDEX());
        LinearLayout.LayoutParams layoutParamsIndex = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 3f);
        txtS1Index.setLayoutParams(layoutParamsIndex);
        txtS1Index.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        Typeface type1 = ResourcesCompat.getFont(ValueApp.contextMarketDetail, R.font.free_sans);
        txtS1Index.setTypeface(type1);
        txtS1Index.setTextColor(ColorApp.colorText);
        txtS1Index.setMaxLines(1);
        txtS1Index.setTextSize(pxToDp((heightItem * 15 / 36)));

        TextView textView = new TextView(ValueApp.contextMarketDetail);
        textView.setText(Html.fromHtml(
                ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtS1UpDow,
                        detailHome.getStrArrow0(), detailHome.getCHGINDEX(), detailHome.getPCTINDEX())));
        LinearLayout.LayoutParams layoutParamstv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        textView.setLayoutParams(layoutParamstv);
        textView.setGravity(Gravity.CENTER | Gravity.LEFT);
        Typeface type = ResourcesCompat.getFont(ValueApp.contextMarketDetail, R.font.free_sans);
        textView.setTypeface(type);
        textView.setTextColor(ColorApp.colorText);
        textView.setMaxLines(1);
        textView.setTextSize(pxToDp((heightItem * 15 / 36)));

        linearLayout1.addView(txtS1Index);
        linearLayout1.addView(textView);

        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 4f);
        LinearLayout linearLayout2 = new LinearLayout(ValueApp.contextMarketDetail);
        linearLayout2.setLayoutParams(layoutParams2);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setGravity(Gravity.CENTER);

        linearLayout2.addView(textView6(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHome.getTOTALQTTY()))));
        linearLayout2.addView(textView6(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHome.getTOTALVALUE()))));

        linearLayout.addView(linearLayout1);
        linearLayout.addView(linearLayout2);

        View view = new View(ValueApp.contextMarketDetail);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        view.setLayoutParams(layoutParams3);
        view.setBackgroundColor(ColorApp.colorGray);

        setColorUpDown(detailHome.getStrArrow0(), textView);

        linearLayoutAll.addView(linearLayout);
        linearLayoutAll.addView(view);
    }

    private TextView textView5(String s) {
        TextView textView = new TextView(ValueApp.contextMarketDetail);
        textView.setText(s);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 3f);
        layoutParams1.setMargins(20, 0, 0, 0);
        textView.setLayoutParams(layoutParams1);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        Typeface type = ResourcesCompat.getFont(ValueApp.contextMarketDetail, R.font.free_sans);
        textView.setTypeface(type);
        textView.setTextColor(ColorApp.colorText);
        textView.setMaxLines(1);
        textView.setTextSize(pxToDp((heightItem * 15 / 36)));
        return textView;
    }

    private TextView textView6(Spanned s) {
        TextView textView = new TextView(ValueApp.contextMarketDetail);
        textView.setText(s);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f);
        textView.setLayoutParams(layoutParams1);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        Typeface type = ResourcesCompat.getFont(ValueApp.contextMarketDetail, R.font.free_sans);
        textView.setTypeface(type);
        textView.setTextColor(ColorApp.colorText);
        textView.setMaxLines(1);
        textView.setTextSize(pxToDp((heightItem * 15 / 36)));
        return textView;
    }

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public void loadDataHNX(DetailHomeHNX detailHomeHNX) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeHNX.getIndex());

        binding.txtVolume.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeHNX.getTotalQty())));
        binding.txtChange.setText(Html.fromHtml(
                ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChange, detailHomeHNX.getIndexChange())));
        binding.txtChangePrecent.setText(Html.fromHtml(
                ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChangePrecent, detailHomeHNX.getIndexChangePer())));
        binding.txtValue.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeHNX.getTotalVal())));

        binding.txtUp.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeHNX.getCountInc())));
        binding.txtBt.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeHNX.getCountEqual())));
        binding.txtDown.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeHNX.getCountDown())));

        binding.txtPutVol.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVol, detailHomeHNX.getKLKhop())));
        binding.txtPutVal.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVal, detailHomeHNX.getGTKhop())));

        setColorUpDown(detailHomeHNX.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeHNX.getStrArrow(), binding.txtChange);
        setColorUpDown(detailHomeHNX.getStrArrow(), binding.txtChangePrecent);
    }

    public void loadDataUPCOM(DetailHomeUpcom detailHomeUpcom) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeUpcom.getIndex());

        binding.txtVolume.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeUpcom.getTongKL())));
        binding.txtChange.setText(Html.fromHtml(
                ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChange, detailHomeUpcom.getIndexChange())));
        binding.txtChangePrecent.setText(Html.fromHtml(
                ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChangePrecent, detailHomeUpcom.getIndexChangePer())));
        binding.txtValue.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeUpcom.getTongGT())));

        binding.txtUp.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeUpcom.getSoMaTang())));
        binding.txtBt.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeUpcom.getSoMaKhongdoi())));
        binding.txtDown.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeUpcom.getSoMaGiam())));

        binding.txtPutVol.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVol, detailHomeUpcom.getKLKhopGDTTCP())));
        binding.txtPutVal.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVal, detailHomeUpcom.getGTKhopGDTTCP())));

        setColorUpDown(detailHomeUpcom.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeUpcom.getStrArrow(), binding.txtChange);
        setColorUpDown(detailHomeUpcom.getStrArrow(), binding.txtChangePrecent);
    }

    public void loadDataVNI30(DetailHomeVNI30 detailHomeVNI30) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeVNI30.getIndex());

        binding.txtVolume.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeVNI30.getTongKL())));
        binding.txtChange.setText(Html.fromHtml(
                ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChange, detailHomeVNI30.getIndexChange())));
        binding.txtChangePrecent.setText(Html.fromHtml(
                ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChangePrecent, detailHomeVNI30.getIndexChangePer())));
        binding.txtValue.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeVNI30.getTongGT())));

        binding.txtUp.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeVNI30.getSomaTang())));
        binding.txtBt.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeVNI30.getSomaKhongdoi())));
        binding.txtDown.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeVNI30.getSomaGiam())));

        binding.txtPutVol.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVol, "0")));
        binding.txtPutVal.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVal, "0")));

        setColorUpDown(detailHomeVNI30.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeVNI30.getStrArrow(), binding.txtChange);
        setColorUpDown(detailHomeVNI30.getStrArrow(), binding.txtChangePrecent);
    }

    public void loadDataHNX_30(DetailHomeHNX30 detailHomeHNX30) {
        binding.txtIndex.setText(sIndex + ": " + detailHomeHNX30.getIndex());

        binding.txtVolume.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Qty, detailHomeHNX30.getTongKL())));
        binding.txtChange.setText(Html.fromHtml(
                ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChange, detailHomeHNX30.getIndexChange())));
        binding.txtChangePrecent.setText(Html.fromHtml(
                ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChangePrecent, detailHomeHNX30.getIndexChangePer())));
        binding.txtValue.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                R.string.marketOverview_detail_txtS1Val, detailHomeHNX30.getTongGT())));

        binding.txtUp.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtUp, detailHomeHNX30.getSomaTang())));
        binding.txtBt.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtBt, detailHomeHNX30.getSomaKhongdoi())));
        binding.txtDown.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtDown, detailHomeHNX30.getSomaGiam())));

        binding.txtPutVol.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVol, "0")));
        binding.txtPutVal.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtPutVal, "0")));

        setColorUpDown(detailHomeHNX30.getStrArrow(), binding.txtIndex);
        setColorUpDown(detailHomeHNX30.getStrArrow(), binding.txtChange);
        setColorUpDown(detailHomeHNX30.getStrArrow(), binding.txtChangePrecent);
    }

    private void setColorUpDown(String s, TextView textView) {
        if (s.equals("▲")) {
            textView.setTextColor(getResources().getColor(R.color.green));
        }

        if (s.equals("■")) {
            textView.setTextColor(getResources().getColor(R.color.orange));
        }

        if (s.equals("▼")) {
            textView.setTextColor(getResources().getColor(R.color.red));
        }
    }

    class reTime extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> arrayList = DataMarketDetail.getKenhSocket(sIndex);
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> arrayList) {
            super.onPostExecute(arrayList);

            ArrayList<TextView> listTextView = getListTextView();

            for (int i = 0; i < arrayList.size(); i++) {
                final int finalI = i;
                ab1.add(args -> {
                    Log.d("StatisticsFragment", "da chay" + args[0] + "  " + arrayList.get(finalI));
                    changeTextview(listTextView.get(finalI), "" + args[0], finalI);
                });

                mSocket.on(arrayList.get(finalI), ab1.get(finalI));
            }

            mSocket.connect();
        }
    }

    Thread thot = new Thread();

    private void changeTextview(TextView textViewFont, String s, int i) {
        thot = new Thread(() -> {
            if (textViewFont.getText().toString().equalsIgnoreCase(s)) {

            } else {
                ValueApp.activityMarketDetail.runOnUiThread(() -> {
                    DecimalFormat format = new DecimalFormat("###,###.##");
                    switch (i) {
                        case 0:
                            String s1;
                            if (s.length() > 5) {
                                s1 = s.substring(0, 5);
                            } else {
                                s1 = s;
                            }
                            binding.txtChangePrecent.setText(Html.fromHtml(
                                    ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChangePrecent, format.format(Double.parseDouble(s1.replace(",", ""))))));
                            setColorUpDown(s, binding.txtChange);
                            break;
                        case 1:
                            String s2;
                            if (s.length() > 5) {
                                s2 = s.substring(0, 5);
                            } else {
                                s2 = s;
                            }
                            binding.txtChange.setText(Html.fromHtml(
                                    ValueApp.contextMarketDetail.getResources().getString(R.string.marketOverview_detail_txtChange, format.format(Double.parseDouble(s2.replace(",", ""))))));
                            setColorUpDown(s, binding.txtChangePrecent);
                            break;
                        case 2:
                            binding.txtIndex.setText(sIndex + ": " + s);
                            break;
                        case 3:
//                            binding.txtVolume.setText(Html.fromHtml(getContext().getResources().getString(
//                                    R.string.marketOverview_detail_txtS1Qty, s)));
                            binding.txtVolume.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                                    R.string.marketOverview_detail_txtS1Qty, format.format(Double.parseDouble(s.replace(",", ""))))));
                            break;
                        case 4:
                            binding.txtValue.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                                    R.string.marketOverview_detail_txtS1Val, format.format(Double.parseDouble(s.replace(",", ""))))));
                            break;
                        case 5:
                            binding.txtUp.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                                    R.string.marketOverview_detail_txtUp, format.format(Double.parseDouble(s.replace(",", ""))))));
                            break;
                        case 6:
                            binding.txtDown.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                                    R.string.marketOverview_detail_txtDown, format.format(Double.parseDouble(s.replace(",", ""))))));
                            break;
                        case 7:
                            binding.txtBt.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                                    R.string.marketOverview_detail_txtBt, format.format(Double.parseDouble(s.replace(",", ""))))));
                            break;
                        case 8:
                            binding.txtPutVol.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                                    R.string.marketOverview_detail_txtPutVol, format.format(Double.parseDouble(s.replace(",", ""))))));
                            break;
                        case 9:
                            binding.txtPutVal.setText(Html.fromHtml(ValueApp.contextMarketDetail.getResources().getString(
                                    R.string.marketOverview_detail_txtPutVal, format.format(Double.parseDouble(s.replace(",", ""))))));
                            break;
                        default:
                            break;
                    }
                });

                ValueApp.activityMarketDetail.runOnUiThread(() -> textViewFont.setBackgroundColor(ColorApp.colorBackgroundDivider));

                Thread.currentThread();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ValueApp.activityMarketDetail.runOnUiThread(() -> textViewFont.setBackgroundColor(ColorApp.colorBackground));
            }
        });
        thot.start();
    }

    public ArrayList<TextView> getListTextView() {
        ArrayList<TextView> list = new ArrayList<>();

        list.add(binding.txtChangePrecent);
        list.add(binding.txtChange);

        list.add(binding.txtIndex);
        list.add(binding.txtVolume);
        list.add(binding.txtValue);

        list.add(binding.txtUp);
        list.add(binding.txtDown);
        list.add(binding.txtBt);

        list.add(binding.txtPutVol);
        list.add(binding.txtPutVal);

        return list;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        thot.interrupt();
    }
}