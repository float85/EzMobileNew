package com.fpts.mobile.eztrading.mainmenu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.chart.ChartFragment;
import com.fpts.mobile.eztrading.common.ColorApp;
import com.fpts.mobile.eztrading.common.ImageApp;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.derivativedetail.DerivativeDetailFragment;
import com.fpts.mobile.eztrading.events.EventsFragment;
import com.fpts.mobile.eztrading.home.HomeFragment;
import com.fpts.mobile.eztrading.main.IMainMenuListener;
import com.fpts.mobile.eztrading.news.HomeNewsFragment;
import com.fpts.mobile.eztrading.watchlistdetail.WatchlistDetailFragment;
import com.fpts.mobile.eztrading.worldindexdetail.WorldIndexDetailFragment;

import java.util.ArrayList;

public class DrawViewMenu extends LinearLayout implements View.OnClickListener {
    private final int ID = 7000000;
    private Context context;
    private int mWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int mHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int mWidthView = mWidth * 4 / 5;
    private int mHeightItem = mHeight / 20;

    private int mTextSize = pxToDp(mHeightItem * 4 / 10);

    private Typeface typeface;
    private Typeface typefaceBold;

    private IMainMenuListener listener;
    private LinearLayout mLinearLayout;
    private ScrollView mScrollView;

    private ArrayList<String> arrayListTitle = new ArrayList<>();
    private ArrayList<Integer> arrayListType = new ArrayList<>();
    private ArrayList<String> arrayListSaved = new ArrayList<>();

    public DrawViewMenu(Context context, IMainMenuListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;

        typeface = ResourcesCompat.getFont(context, R.font.free_sans);
        typefaceBold = ResourcesCompat.getFont(context, R.font.free_sans_bold);

        LinearLayout.LayoutParams params = new LayoutParams(mWidthView, ViewGroup.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(params);
        this.setOrientation(VERTICAL);
        setBackgroundColor(ColorApp.colorBackground);

        initData();

    }

    private void initData() {
        DataMenu.init(context);
        arrayListTitle = DataMenu.getArrayListTitle();
        arrayListType = DataMenu.getArrayListType(context);
        arrayListSaved = DataMenu.getArrayListSaved();

        this.addView(viewAccount());
        mScrollView = new ScrollView(context);
        mLinearLayout = new LinearLayout(context);
        mScrollView.setVerticalScrollBarEnabled(false);
        mScrollView.setHorizontalScrollBarEnabled(false);
        mScrollView.addView(mLinearLayout);
        mLinearLayout.addView(viewContent());
        mLinearLayout.addView(viewSetting());

        mLinearLayout.getChildAt(1).setVisibility(GONE);
        this.addView(mScrollView);
    }

    private LinearLayout viewAccount() {
        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setPadding(8, 12, 8, 12);
        linearLayout.setHorizontalGravity(HORIZONTAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundTable);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams paramsImg = new LayoutParams(mWidth / 12, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsImg.setMargins(4, 4, 4, 4);
        ImageView account = new ImageView(context);
        account.setId(ID + 1000);
        account.setOnClickListener(this);
        account.setLayoutParams(paramsImg);
        account.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        account.setImageDrawable(ImageApp.iconMenuAccount);
        account.setPadding(12, 12, 12, 12);
        linearLayout.addView(account);

        LinearLayout linearlayoutUser = new LinearLayout(context);
        LayoutParams paramsUser = new LayoutParams(mWidth * 9 / 12, ViewGroup.LayoutParams.MATCH_PARENT);
        paramsUser.weight = 1;
        linearlayoutUser.setLayoutParams(paramsUser);
        linearlayoutUser.setGravity(Gravity.CENTER_VERTICAL);
        linearlayoutUser.setOrientation(VERTICAL);
        TextView userCode = new TextView(context);
        userCode.setId(ID + 1000 + 1);
        userCode.setOnClickListener(this);
        userCode.setTextColor(ColorApp.colorTextToolBar);
        userCode.setGravity(Gravity.CENTER_VERTICAL);
        userCode.setSingleLine();
        userCode.setMaxLines(1);
        userCode.setTypeface(typeface);
        TextView userName = new TextView(context);
        userName.setId(ID + 1000 + 2);
        userName.setOnClickListener(this);
        userName.setTextColor(ColorApp.colorTextToolBar);
        userName.setGravity(Gravity.CENTER_VERTICAL);
        userName.setMaxLines(1);
        userName.setSingleLine();
        userName.setTypeface(typefaceBold);
        ArrayList<String> arrayList = DataMenu.getUserInfo(context);
        if (arrayList != null && arrayList.size() > 1) {
            userCode.setText(arrayList.get(0));
            userName.setText(arrayList.get(1));
        }
        linearlayoutUser.addView(userCode);
        linearlayoutUser.addView(userName);
        linearLayout.addView(linearlayoutUser);

        ImageView saved = new ImageView(context);
        saved.setId(ID + 1000 + 3);
        saved.setOnClickListener(this);
        saved.setLayoutParams(paramsImg);
        saved.setImageDrawable(ImageApp.iconMenuSaved);
        saved.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        saved.setPadding(12, 12, 12, 12);
        linearLayout.addView(saved);
        linearLayout.getChildAt(2).setVisibility(GONE);

        ImageView settings = new ImageView(context);
        settings.setId(ID + 1000 + 4);
        settings.setOnClickListener(this);
        settings.setLayoutParams(paramsImg);
        settings.setImageDrawable(ImageApp.iconMenuSettings);
        settings.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        settings.setPadding(12, 12, 12, 12);
        linearLayout.addView(settings);

        ImageView logout = new ImageView(context);
        logout.setId(ID + 1000 + 5);
        logout.setOnClickListener(this);
        logout.setLayoutParams(paramsImg);
        logout.setImageDrawable(ImageApp.iconMenuLogout);
        logout.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        logout.setPadding(12, 12, 12, 12);
        linearLayout.addView(logout);

        return linearLayout;
    }

    private LinearLayout viewContent() {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(VERTICAL);
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 2;
        linearLayout.setLayoutParams(params);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundTable);

        linearLayout.addView(linearLayout1(0));
        linearLayout.addView(viewFavorite());

        for (int i = 1; i < arrayListType.size(); i++)
            if (arrayListSaved.get(i).equals("0"))
                switch (arrayListType.get(i)) {
                    case 1:
                        linearLayout.addView(linearLayout1(i));
                        break;
                    case 2:
                        linearLayout.addView(linearLayout2(i, false));
                        break;
                    case 3:
                        linearLayout.addView(linearLayout3(i));
                        break;
                    default:
                        break;
                }
        return linearLayout;
    }

    private LinearLayout viewFavorite() {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);

        for (int i = 0; i < arrayListType.size(); i++) {
            if (arrayListSaved.get(i).equalsIgnoreCase("1")) {
                if (arrayListType.get(i) == 2)
                    linearLayout.addView(linearLayout2(i, true));
                else if (arrayListType.get(i) == 3) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (arrayListType.get(j) == 2) {
                            linearLayout.addView(linearLayout2(j, true));
                            break;
                        }
                    }
                    linearLayout.addView(linearLayout3(i));
                    for (int j = i + 1; j < arrayListType.size(); j++) {
                        if (arrayListType.get(j) == 3 && arrayListSaved.get(j).equalsIgnoreCase("1")) {
                            linearLayout.addView(linearLayout3(j));
                        } else if (arrayListType.get(j) == 2) {
                            i = j;
                            break;
                        }
                    }
                }

            }
        }
        return linearLayout;
    }

    private LinearLayout viewSetting() {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 2;
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundTable);
        for (int i = 1; i < arrayListType.size(); i++) {
            switch (arrayListType.get(i)) {
                case 1:
                    linearLayout.addView(linearLayoutSetting1(i));
                    break;
                case 2:
                    linearLayout.addView(linearLayoutSetting2(i));
                    break;
                case 3:
                    linearLayout.addView(linearLayoutSetting3(i));
                    break;
            }
        }
        return linearLayout;
    }

    private LinearLayout linearLayout1(int pos) {
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
        params.topMargin = 1;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(ID + pos);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setPadding(mWidthView / 20, 0, 0, 0);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundMenuHeader);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        TextView textView = new TextView(context);
        textView.setText(arrayListTitle.get(pos));
        textView.setTextSize(mTextSize);
        textView.setTypeface(typefaceBold);
        textView.setAllCaps(true);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextColor(ColorApp.colorText);

        linearLayout.addView(textView);
        linearLayout.setOnClickListener(this);
        return linearLayout;
    }

    private LinearLayout linearLayout2(int pos, boolean haveChildSaved) {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
        params.topMargin = 1;

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(haveChildSaved ? ID + arrayListType.size() + pos : ID + pos);
        linearLayout.setLayoutParams(params);
        linearLayout.setPadding(mWidthView * 1 / 20, 0, 0, 0);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
        params1.gravity = Gravity.CENTER_VERTICAL;
        params1.weight = 1;
        TextView textView = new TextView(context);
        textView.setLayoutParams(params1);
        textView.setText(arrayListTitle.get(pos));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextSize(mTextSize);
        textView.setTypeface(typeface);
        textView.setTextColor(ColorApp.colorText);

        linearLayout.addView(textView);

        if (getChildCount(pos) > 0) {
            textView.setTypeface(typefaceBold);
            LayoutParams params2 = new LayoutParams(mWidthView / 7, mHeightItem);
            params2.gravity = Gravity.CENTER;
            ImageView imageView = new ImageView(context);
            imageView.setImageDrawable(ImageApp.iconMenuArrowUp);
            imageView.setLayoutParams(params2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            linearLayout.addView(imageView);
        }
        linearLayout.setOnClickListener(this);
        return linearLayout;
    }

    private LinearLayout linearLayout3(int pos) {
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
//        params.topMargin = 1;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(ID + pos);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setPadding(mWidthView * 2 / 20, 0, 0, 0);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        TextView textView = new TextView(context);
        textView.setText(arrayListTitle.get(pos));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextSize(mTextSize);
        textView.setTextColor(ColorApp.colorText);
        textView.setTypeface(typeface);

        linearLayout.addView(textView);
        linearLayout.setOnClickListener(this);
        return linearLayout;
    }

    private LinearLayout linearLayoutSetting1(int pos) {
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
        params.topMargin = 1;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(ID + 2000 + pos);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setPadding(mWidthView / 20, 0, 0, 0);
        linearLayout.setBackgroundColor(ColorApp.colorBackgroundMenuHeader);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        TextView textView = new TextView(context);
        textView.setText(arrayListTitle.get(pos));
        textView.setTextSize(mTextSize);
        textView.setTypeface(typefaceBold);
        textView.setAllCaps(true);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextColor(ColorApp.colorText);

        linearLayout.addView(textView);
        return linearLayout;
    }

    private LinearLayout linearLayoutSetting2(int pos) {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
        params.topMargin = 1;

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(ID + 2000 + pos);
        linearLayout.setLayoutParams(params);
        linearLayout.setPadding(mWidthView * 1 / 20, 0, 0, 0);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        if (getChildCount(pos) == 0) {
            linearLayout.setPadding(0, 0, 0, 0);
            boolean isCheck = DataMenu.isFavorite(context, pos);
            LayoutParams params2 = new LayoutParams(mWidthView / 7, mHeightItem);
            params2.gravity = Gravity.CENTER;
            ImageView imageView = new ImageView(context);
            imageView.setId(ID + 2000 + arrayListType.size() + pos);
            imageView.setImageDrawable(isCheck ? ImageApp.iconMenuStarChecked : ImageApp.iconMenuStarUnChecked);
            imageView.setLayoutParams(params2);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setOnClickListener(this);
            linearLayout.addView(imageView);
        }

        LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
        params1.gravity = Gravity.CENTER_VERTICAL;
        params1.weight = 1;
        TextView textView = new TextView(context);
        textView.setLayoutParams(params1);
        textView.setText(arrayListTitle.get(pos));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextSize(mTextSize);
        textView.setTypeface(typefaceBold);
        textView.setTextColor(ColorApp.colorText);

        linearLayout.addView(textView);
        return linearLayout;
    }

    private LinearLayout linearLayoutSetting3(int pos) {
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeightItem);
//        params.topMargin = 1;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setId(ID + 2000 + pos);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setBackgroundColor(ColorApp.colorBackground);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);

        boolean isCheck = DataMenu.isFavorite(context, pos);
        LayoutParams params2 = new LayoutParams(mWidthView / 7, mHeightItem);
        params2.gravity = Gravity.CENTER;
        ImageView imageView = new ImageView(context);
        imageView.setId(ID + 2000 + arrayListType.size() + pos);
        imageView.setImageDrawable(isCheck ? ImageApp.iconMenuStarChecked : ImageApp.iconMenuStarUnChecked);
        imageView.setLayoutParams(params2);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setOnClickListener(this);


        TextView textView = new TextView(context);
        textView.setText(arrayListTitle.get(pos));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setTextSize(mTextSize);
        textView.setTextColor(ColorApp.colorText);
        textView.setTypeface(typeface);

        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        return linearLayout;
    }

    @Override
    public void onClick(View view) {
        int arrayListTypeSize = arrayListType.size();
        int id = view.getId();
        if (id == ID + 2 || id == ID + arrayListTypeSize + 2) {
            listener.onItemClick(HomeFragment.newInstance());
        } else if (id == ID + 3 || id == ID + arrayListTypeSize + 3) {
            listener.onItemClick(WatchlistDetailFragment.newInstance());
        } else if (id == ID + 4 || id == ID + arrayListTypeSize + 4) {
            listener.onItemClick(DerivativeDetailFragment.newInstance());
        } else if (id == ID + 5 || id == ID + arrayListTypeSize + 5) {
            listener.onItemClick(HomeNewsFragment.newInstance());
        } else if (id == ID + 6 || id == ID + arrayListTypeSize + 6) {
            listener.onItemClick(EventsFragment.newInstance());
        } else if (id == ID + 7 || id == ID + arrayListTypeSize + 7) {
            listener.onItemClick(ChartFragment.newInstance());
        } else if (id == ID + 8 || id == ID + arrayListTypeSize + 8) {
            listener.onItemClick(WorldIndexDetailFragment.newInstance());
        } else if (id == ID + 1000 + 0) {//account
        } else if (id == ID + 1000 + 1) { //code
        } else if (id == ID + 1000 + 2) { //name
        } else if (id == ID + 1000 + 3) {//saved
            findViewById(ID + 1000 + 3).setVisibility(GONE);
            findViewById(ID + 1000 + 4).setVisibility(VISIBLE);
            findViewById(ID + 1000 + 5).setVisibility(VISIBLE);
            mLinearLayout.getChildAt(0).setVisibility(VISIBLE);
            mLinearLayout.getChildAt(1).setVisibility(GONE);
            DataMenu.saveFavorite(context, arrayListSaved);

            this.removeAllViews();
            initData();
        } else if (id == ID + 1000 + 4) {//setting
            findViewById(ID + 1000 + 3).setVisibility(VISIBLE);
            findViewById(ID + 1000 + 4).setVisibility(GONE);
            findViewById(ID + 1000 + 5).setVisibility(VISIBLE);
            mLinearLayout.getChildAt(0).setVisibility(GONE);
            mLinearLayout.getChildAt(1).setVisibility(VISIBLE);

        } else if (id == ID + 1000 + 5) {//logout
        } else {
            if (id < ID + 1000) {//Content
                onClickContent(view, id);
            } else if (id >= ID + 2000) {
                onClickSetting(view, id);
            }
        }
    }

    private void onClickContent(View view, int id) {
        if (view.getId() - arrayListType.size() - ID > 0) {//Type = 2 phần favorite

        } else if (view.getId() - ID >= 0) {//phần dưới
            int post = view.getId() - ID;
            int count = getChildCount(post);
            if (count == 0) {

            } else {
                if (post == 0) {
                    boolean isVisible = false;
                    int temp = 0;
                    for (int i = 0; i < arrayListType.size(); i++) {
                        if (arrayListSaved.get(i).equalsIgnoreCase("1")) {
                            if (arrayListType.get(i) == 3) {
                                if (findViewById(ID + i).getVisibility() == VISIBLE) {
                                    isVisible = true;
                                    temp = i;
                                }
                            } else if (arrayListType.get(i) == 2) {
                                if (findViewById(ID + arrayListType.size() + i).getVisibility() == VISIBLE) {
                                    isVisible = true;
                                    temp = i;
                                }
                            }
                            break;
                        }
                    }
                    for (int i = temp; i < arrayListType.size(); i++) {
                        if (arrayListType.get(i) == 1) {

                        } else if (arrayListType.get(i) == 2) {
                            if (arrayListSaved.get(i).equalsIgnoreCase("1"))
                                findViewById(ID + arrayListType.size() + i).setVisibility(isVisible ? GONE : VISIBLE);
                        } else if (arrayListType.get(i) == 3) {
                            if (arrayListSaved.get(i).equalsIgnoreCase("1")) {
                                findViewById(ID + i).setVisibility(isVisible ? GONE : VISIBLE);
                                for (int j = i - 1; j > 0; j--) {
                                    if (arrayListType.get(j) == 2) {
                                        findViewById(ID + arrayListType.size() + j).setVisibility(isVisible ? GONE : VISIBLE);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    boolean check = true;
                    int postOfFirstChild = getPostOfFirstChild(post);
                    if (postOfFirstChild > 0) {
                        LinearLayout first = findViewById(ID + postOfFirstChild);
                        if (first != null) {
                            if (first.getVisibility() == VISIBLE) {
                                check = false;
                                for (int i = ID + postOfFirstChild; i <= id + count; i++) {
                                    LinearLayout linearLayout = findViewById(i);
                                    if (linearLayout != null && arrayListSaved.get(i - ID).equalsIgnoreCase("0"))
                                        linearLayout.setVisibility(GONE);
                                }
                            } else {
                                check = true;
                                for (int i = ID + postOfFirstChild; i <= id + count; i++) {
                                    LinearLayout linearLayout = findViewById(i);
                                    if (linearLayout != null && arrayListSaved.get(i - ID).equalsIgnoreCase("0"))
                                        linearLayout.setVisibility(VISIBLE);
                                }
                            }
                        }
                        if (arrayListType.get(post) == 2) {
                            ImageView imageView = (ImageView) ((LinearLayout) findViewById(id)).getChildAt(1);
                            imageView.setImageDrawable(check ? ImageApp.iconMenuArrowUp : ImageApp.iconMenuArrowDown);
                            TextView textView = (TextView) ((LinearLayout) findViewById(id)).getChildAt(0);
                            textView.setTypeface(check ? typefaceBold : typeface);
                        }
                    }
                }
            }
        }
    }

    private void onClickSetting(View view, int id) {
        //SETTING
        if (view.getId() - ID - 2000 - arrayListType.size() > 0) {
            //ảnh
            int pos = view.getId() - ID - 2000 - arrayListType.size();

            if (arrayListSaved.get(pos).equalsIgnoreCase("1")) {
                ((ImageView) findViewById(view.getId())).setImageDrawable(ImageApp.iconMenuStarUnChecked);
                arrayListSaved.set(pos, "0");
            } else {
                ((ImageView) findViewById(view.getId())).setImageDrawable(ImageApp.iconMenuStarChecked);
                arrayListSaved.set(pos, "1");
            }
        } else {//text
            int pos = view.getId() - ID - 2000;
            int count = getChildCount(pos);
            if (arrayListType.get(pos) == 1) {
                if (count > 0) {
                    LinearLayout first = findViewById(id + 1);
                    if (first != null) {
                        if (first.getVisibility() == VISIBLE)
                            for (int i = id + 1; i < id + count; i++)
                                findViewById(i).setVisibility(GONE);
                        else
                            for (int i = id + 1; i < id + count; i++)
                                findViewById(i).setVisibility(VISIBLE);

                    }
                }
            } else if (arrayListType.get(pos) == 2) {
                if (count == 0) {
                    ImageView imageView = findViewById(id + arrayListType.size());
                    if (arrayListSaved.get(pos).equalsIgnoreCase("1")) {
                        ((ImageView) findViewById(view.getId())).setImageDrawable(ImageApp.iconMenuStarUnChecked);
                        arrayListSaved.set(pos, "0");
                    } else {
                        ((ImageView) findViewById(view.getId())).setImageDrawable(ImageApp.iconMenuStarChecked);
                        arrayListSaved.set(pos, "1");
                    }

                }
            }
        }
    }

    private int getChildCount(int pos) {
        int count = 0;
        if (pos == 0) {
            for (int i = 0; i < arrayListType.size(); i++) {
                if (arrayListSaved.get(i).equalsIgnoreCase("1"))
                    count++;
            }
            return count;
        }
        int type = arrayListType.get(pos);
        if (pos == arrayListType.size() - 1)
            return 0;


        for (int i = pos + 1; i < arrayListType.size(); i++) {
            if (type < arrayListType.get(i))
                count++;
            else break;
        }
        return count;
    }

    private int getPostOfFirstChild(int pos) {
        if (pos == arrayListType.size() - 1)
            return -1;

        int type = arrayListType.get(pos);
        for (int i = pos + 1; i < arrayListType.size(); i++) {
            if (type < arrayListType.get(i) && arrayListSaved.get(i).equalsIgnoreCase("0"))
                return i;
        }

        return -1;
    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    private void setFragment(Fragment fragment) {
        ValueApp.fragmentTransaction.replace(R.id.linear_layout, fragment);
        ValueApp.fragmentTransaction.commit();
    }
}
