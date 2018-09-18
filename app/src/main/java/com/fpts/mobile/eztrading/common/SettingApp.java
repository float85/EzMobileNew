package com.fpts.mobile.eztrading.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.fpts.mobile.eztrading.R;

public class SettingApp {
    private static Context context;

    public SettingApp(Context context) {
        this.context = context;
        init();
    }

    public void setTheme(Activity activity) {
        changeTheme(activity);
    }

    // TODO: HoaDT 7/24/2018 1:17 PM Khởi tạo theme, ngôn ngữ
    private void init() {
        String str = FileInputAndOutputStream.readData(context, "language_app");
        if (str == null || str.equalsIgnoreCase("") || str.equalsIgnoreCase("VI")) {
            setLanguageVn();
        } else {
            setLanguageEn();
        }

        String theme = FileInputAndOutputStream.readData(context, "theme_app");

        if (theme == null || theme.equalsIgnoreCase("") || theme.equalsIgnoreCase("1")) {
            setThemeLight();
            setImageAppLight();
        } else {
            setThemeDark();
            setImageAppDark();
        }

        setColor();
        setImageApp();
    }

    private void setColor() {
        ColorApp.colorBackgroundToolBar = context.getResources().getColor(R.color.colorBackgroundToolBar);
        ColorApp.colorTextToolBar = context.getResources().getColor(R.color.black);
        ColorApp.colorBackgroundMenuHeader = context.getResources().getColor(R.color.gray_1);
        ColorApp.TRANSPARENT = Color.TRANSPARENT;
        ColorApp.colorGreen = context.getResources().getColor(R.color.green);
        ColorApp.colorWhite = context.getResources().getColor(R.color.white);
        ColorApp.colorBlack = context.getResources().getColor(R.color.black);
        ColorApp.colorGray = context.getResources().getColor(R.color.gray);
        ColorApp.colorBlue = context.getResources().getColor(R.color.blue);
        ColorApp.colorRed = context.getResources().getColor(R.color.red);

        ColorApp.colorTextRef = context.getResources().getColor(R.color.orange);
        ColorApp.colorTextCeiling = context.getResources().getColor(R.color.purple);
        ColorApp.colorTextFloor = context.getResources().getColor(R.color.blue);
        ColorApp.colorTextUp = context.getResources().getColor(R.color.green);
        ColorApp.colorTextDown = context.getResources().getColor(R.color.red);
        ColorApp.colorTextChange = context.getResources().getColor(R.color.orange);
    }

    private void setImageApp() {
//        ImageApp.iconArrowChange = context.getResources().getDrawable(R.drawable.icon_arrow_upperright);
        ImageApp.iconHomeMenu = context.getResources().getDrawable(R.drawable.ic_dehaze_black_24dp);
        ImageApp.iconMenuOverview = context.getResources().getDrawable(R.drawable.ic_overview);
        ImageApp.iconMenuProperty = context.getResources().getDrawable(R.drawable.ic_property);
        ImageApp.iconMenuPlacingOrder = context.getResources().getDrawable(R.drawable.ic_order_placing);
        ImageApp.iconMenuMoneyTransfer = context.getResources().getDrawable(R.drawable.ic_money_transfer);
        ImageApp.iconMenuEditDelete = context.getResources().getDrawable(R.drawable.ic_edit_delete);
        ImageApp.iconMenuAccount = context.getResources().getDrawable(R.drawable.ic_account);
        ImageApp.iconMenuSettings = context.getResources().getDrawable(R.drawable.ic_settings);
        ImageApp.iconMenuLogout = context.getResources().getDrawable(R.drawable.ic_logout);
        ImageApp.iconMenuStar = context.getResources().getDrawable(R.drawable.ic_menu_star);
        ImageApp.iconMenuStarChecked = context.getResources().getDrawable(R.drawable.ic_menu_star_checked);
        ImageApp.iconMenuStarUnChecked = context.getResources().getDrawable(R.drawable.ic_menu_star_unchecked);
        ImageApp.iconMenuArrowDown = context.getResources().getDrawable(R.drawable.ic_arrow_down);
        ImageApp.iconMenuArrowUp = context.getResources().getDrawable(R.drawable.ic_arrow_up);
        ImageApp.iconMenuSaved = context.getResources().getDrawable(R.drawable.ic_save);

        ImageApp.iconMarketDetailCeiling = context.getResources().getDrawable(R.drawable.icon_arrow_ceiling);
        ImageApp.iconMarketDetailUp = context.getResources().getDrawable(R.drawable.icon_arrow_up);
        ImageApp.iconMarketDetailNoChange = context.getResources().getDrawable(R.drawable.icon_arrow_ref);
        ImageApp.iconMarketDetailDown = context.getResources().getDrawable(R.drawable.icon_arrow_down);
        ImageApp.iconMarketDetailFloor = context.getResources().getDrawable(R.drawable.icon_arrow_floor);

        ImageApp.iconNews = context.getResources().getDrawable(R.drawable.icon_news);
    }

    private void changeTheme(Activity activity) {
        String theme = FileInputAndOutputStream.readData(context, "theme_app");
        // TODO: HoaDT 7/24/2018 3:35 PM nếu đang là theme sáng => tối
        if (theme == null || theme == "" || theme == "1") {
            activity.setTheme(R.style.Theme_MyDarkTheme);
            setThemeDark();
            FileInputAndOutputStream.saveData(context, "0", "theme_app");

        } else {
            activity.setTheme(R.style.Theme_MyLightTheme);
            setThemeLight();
            FileInputAndOutputStream.saveData(context, "1", "theme_app");
        }
    }

    // TODO: HoaDT 7/24/2018 1:17 PM
    private static void setThemeLight() {
        Log.w("SettingApp", "setThemeLight: ");
        ColorApp.colorBackground = context.getResources().getColor(R.color.colorBackground);
        ColorApp.colorBackgroundDivider = context.getResources().getColor(R.color.gray);
        ColorApp.colorBackgroundTable = context.getResources().getColor(R.color.colorBackgroundTable);
        ColorApp.colorBackgroundSidebar = context.getResources().getColor(R.color.colorBackground);

        ColorApp.colorBackgroundTablelayout = context.getResources().getColor(R.color.colorBackground);
        ColorApp.colorBackgroundTablelayoutSelect = context.getResources().getColor(R.color.colorContentDark);
        ColorApp.colorTextTablelayout = context.getResources().getColor(R.color.gray);
        ColorApp.colorTextTablelayoutSelected = context.getResources().getColor(R.color.colorFont);

        ColorApp.colorBackgroundHeader = context.getResources().getColor(R.color.colorBackground);
        ColorApp.colorBackgroundHeaderGray = context.getResources().getColor(R.color.gray);
        ColorApp.colorTextHeader = context.getResources().getColor(R.color.black);
        ColorApp.colorBackgroundHeaderSub = context.getResources().getColor(R.color.white);
        ColorApp.colorTextHeaderSub = context.getResources().getColor(R.color.colorBackground);

        ColorApp.colorText = context.getResources().getColor(R.color.black);
        ColorApp.colorTextSub = context.getResources().getColor(R.color.gray);
        ColorApp.colorTextSymbol = context.getResources().getColor(R.color.blue);
        ColorApp.colorTextSidebar = context.getResources().getColor(R.color.white);
        ColorApp.colorTextNewsDate = context.getResources().getColor(R.color.blue);
        ColorApp.colorTextBackgroundChange = context.getResources().getColor(R.color.colorTextBackgroundChange);
    }

    private static void setThemeDark() {
        Log.w("SettingApp", "setThemeDark: ");
        ColorApp.colorBackground = context.getResources().getColor(R.color.colorBackgroundDark);
        ColorApp.colorBackgroundDivider = context.getResources().getColor(R.color.gray);
        ColorApp.colorBackgroundTable = context.getResources().getColor(R.color.colorBackgroundDark);
        ColorApp.colorBackgroundSidebar = context.getResources().getColor(R.color.colorBackgroundDark);

        ColorApp.colorBackgroundTablelayout = context.getResources().getColor(R.color.colorBackgroundDark);
        ColorApp.colorBackgroundTablelayoutSelect = context.getResources().getColor(R.color.colorContent);
        ColorApp.colorTextTablelayout = context.getResources().getColor(R.color.gray);
        ColorApp.colorTextTablelayoutSelected = context.getResources().getColor(R.color.colorFontDark);

        ColorApp.colorBackgroundHeader = context.getResources().getColor(R.color.colorBackgroundDark);
        ColorApp.colorBackgroundHeaderGray = context.getResources().getColor(R.color.colorBackgroundDark);
        ColorApp.colorTextHeader = context.getResources().getColor(R.color.white);
        ColorApp.colorBackgroundHeaderSub = context.getResources().getColor(R.color.black);
        ColorApp.colorTextHeaderSub = context.getResources().getColor(R.color.colorBackgroundDark);

        ColorApp.colorText = context.getResources().getColor(R.color.white);
        ColorApp.colorTextSub = context.getResources().getColor(R.color.gray);
        ColorApp.colorTextSymbol = context.getResources().getColor(R.color.blue);
        ColorApp.colorTextSidebar = context.getResources().getColor(R.color.black);
        ColorApp.colorTextNewsDate = context.getResources().getColor(R.color.blue);
    }

    private static void setLanguageEn() {

    }

    private static void setLanguageVn() {

    }

    private void setImageAppLight() {
        ImageApp.iconHomeAdd = context.getResources().getDrawable(R.drawable.ic_add);
        ImageApp.iconHomeEdit = context.getResources().getDrawable(R.drawable.ic_edit);
        ImageApp.iconArrowRight = context.getResources().getDrawable(R.drawable.ic_arrow_right);
        ImageApp.iconArrowLeft = context.getResources().getDrawable(R.drawable.ic_arrow_left);
        ImageApp.iconMarketStar = context.getResources().getDrawable(R.drawable.ic_star_border_black_24dp);
        ImageApp.iconMarketStarMarked = context.getResources().getDrawable(R.drawable.ic_star_black_24dp);
    }

    private void setImageAppDark() {
        ImageApp.iconHomeAdd = context.getResources().getDrawable(R.drawable.ic_add_white);
        ImageApp.iconHomeEdit = context.getResources().getDrawable(R.drawable.ic_edit_white);
        ImageApp.iconArrowRight = context.getResources().getDrawable(R.drawable.ic_arrow_right_white);
        ImageApp.iconArrowLeft = context.getResources().getDrawable(R.drawable.ic_arrow_left_white);
//        ImageApp.iconArrowChange = context.getResources().getDrawable(R.drawable.icon_arrow_upperright);
        ImageApp.iconHomeMenu = context.getResources().getDrawable(R.drawable.ic_dehaze_white_24dp);
        ImageApp.iconMarketStar = context.getResources().getDrawable(R.drawable.ic_star_border_white_24dp);
        ImageApp.iconMarketStarMarked = context.getResources().getDrawable(R.drawable.ic_star_white_24dp);
    }
}
