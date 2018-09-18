package com.fpts.mobile.eztrading.main;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;

import java.util.ArrayList;

public class NavigationBottomMain extends LinearLayout {
    Context context;
    public NavigationBottomMain(Context context) {
        super(context);
        this.context = context;
    }

    public LinearLayout painNavigationBottom(Context context, int height) {
        ArrayList<String> listText = getListTextView(context);
        ArrayList<Integer> listImage = getListImageView(context);

        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorBackgroundDark));
        linearLayout.setOrientation(HORIZONTAL);

        ArrayList<LinearLayout> listLinear = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            LinearLayout linearLayout1 = new LinearLayout(context);
            LinearLayout.LayoutParams layoutParams1 = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
            linearLayout1.setLayoutParams(layoutParams1);
            linearLayout1.setOrientation(VERTICAL);

            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams paramsImageView = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.6f);
            imageView.setImageResource(listImage.get(i));
            imageView.setPadding(0, 10, 0, 5);
            imageView.setLayoutParams(paramsImageView);

            TextView textView = new TextView(context);
            LinearLayout.LayoutParams paramsText = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 0.4f);
            textView.setLayoutParams(paramsText);
            textView.setTextSize(height * 11 / 32);
            textView.setPadding(0, 0, 0, 3);
            textView.setText(listText.get(i));
            textView.setTextColor(context.getResources().getColor(R.color.colorFontDark));
            textView.setGravity(Gravity.CENTER);

            linearLayout1.addView(imageView);
            linearLayout1.addView(textView);

            linearLayout.addView(linearLayout1);

            listLinear.add(linearLayout1);
        }

        setBackgroundLinear(listLinear, 0);
        for (int i = 0; i < listLinear.size(); i++) {
            int finalI = i;
            listLinear.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setBackgroundLinear(listLinear, finalI);
                }
            });
        }

        return linearLayout;
    }

    private void setBackgroundLinear(ArrayList<LinearLayout> linearLayouts, int i) {
        if (i == 0) {
            linearLayouts.get(0).setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
            linearLayouts.get(1).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(2).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(3).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(4).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
        } else if (i == 1) {
            linearLayouts.get(0).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(1).setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
            linearLayouts.get(2).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(3).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(4).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
        } else if (i == 2) {
            linearLayouts.get(0).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(1).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(2).setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
            linearLayouts.get(3).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(4).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
        } else if (i == 3) {
            linearLayouts.get(0).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(1).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(2).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(3).setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
            linearLayouts.get(4).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
        } else if (i == 4) {
            linearLayouts.get(0).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(1).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(2).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(3).setBackgroundResource(R.drawable.bg_item_menu_tab_dark);
            linearLayouts.get(4).setBackgroundResource(R.drawable.bg_item_menu_tab_select_dark);
        }
    }

    private ArrayList<String> getListTextView(Context context) {
        ArrayList<String> list = new ArrayList<>();
        list.add(getContext().getString(R.string.home_menutab_overview));
        list.add(context.getString(R.string.home_menutab_property));
        list.add(context.getString(R.string.home_menutab_order_placing));
        list.add(context.getString(R.string.home_menutab_del_edit));
        list.add(getContext().getString(R.string.home_menutab_money_transfer));
        return list;
    }

    private ArrayList<Integer> getListImageView(Context context) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.ic_overview);
        list.add(R.drawable.ic_property);
        list.add(R.drawable.ic_order_placing);
        list.add(R.drawable.ic_edit_delete);
        list.add(R.drawable.ic_money_transfer);
        return list;
    }
}
