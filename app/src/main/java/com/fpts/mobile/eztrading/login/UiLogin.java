package com.fpts.mobile.eztrading.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ID;

public class UiLogin extends LinearLayout implements View.OnClickListener {
    Context context;
    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    protected int height = Resources.getSystem().getDisplayMetrics().heightPixels;

    public UiLogin(Context context) {
        super(context);
        this.context = context;
        LayoutParams paramsRoot = new LayoutParams(width, height);
        this.setOrientation(VERTICAL);

        this.setLayoutParams(paramsRoot);
        this.addView(setImgLogo());
        this.addView(setUiControl());
        this.addView(setHelp());
    }

    private RelativeLayout setImgLogo() {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, (int) (height * 0.36));
        params.setMargins(1, 1, 1, 1);
        relativeLayout.setLayoutParams(params);

        //Image Logo
        LayoutParams prLogo = new LayoutParams(width / 3, height / 3);
        ImageView imgLogo = new ImageView(context);
        imgLogo.setLayoutParams(prLogo);
        imgLogo.setImageDrawable(context.getResources().getDrawable(R.drawable.logo_login));
        imgLogo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(imgLogo);
        return relativeLayout;
    }


    @SuppressLint("NewApi")
    private LinearLayout setUiControl() {
        LinearLayout linearLayout = new LinearLayout(context);
        LayoutParams paramsControl = new LayoutParams(width, (int) (height * 0.38));

        linearLayout.setLayoutParams(paramsControl);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setPadding(50, 0, 50, 0);

        //UserName
        EditText etUser = new EditText(context);
        LayoutParams paramsEtUser = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        paramsEtUser.gravity = Gravity.CENTER;
        etUser.setLayoutParams(paramsEtUser);

        etUser.setHint(context.getResources().getString(R.string.username));
        etUser.setHintTextColor(context.getResources().getColor(R.color.color_kengang));
        etUser.setMaxLines(1);
        etUser.setGravity(Gravity.CENTER);
        etUser.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        etUser.setInputType(InputType.TYPE_CLASS_TEXT);
        etUser.setBackground(context.getResources().getDrawable(R.drawable.border_edittext_login));
        etUser.setId(ID.setID("username"));
        etUser.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        //PassWord
        EditText etPass = new EditText(context);
        LayoutParams paramsEtPass = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        paramsEtPass.gravity = Gravity.CENTER;
        paramsEtPass.setMargins(0, 30, 0, 0);
        etPass.setLayoutParams(paramsEtPass);

        etPass.setHint(context.getResources().getString(R.string.password));
        etPass.setHintTextColor(context.getResources().getColor(R.color.color_kengang));
        etPass.setMaxLines(1);
        etPass.setGravity(Gravity.CENTER);
        etPass.setInputType(InputType.TYPE_CLASS_TEXT);
        etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        etPass.setBackground(context.getResources().getDrawable(R.drawable.border_edittext_login));
        etPass.setId(ID.setID("password"));

        //Button Config
        Button btnConfig = new Button(context);
        LayoutParams paramsBtnConfig = new LayoutParams(width / 2, 0, 1);
        paramsBtnConfig.gravity = Gravity.CENTER;
        btnConfig.setLayoutParams(paramsBtnConfig);
        btnConfig.setTextColor(context.getResources().getColor(R.color.tabSelectedTextColor_white));
        btnConfig.setBackground(context.getResources().getDrawable(R.drawable.register_login_button));
        btnConfig.setText(context.getResources().getString(R.string.login));
        btnConfig.setId(ID.setID("btnConfig"));

        linearLayout.addView(etUser);
        linearLayout.addView(etPass);
        linearLayout.addView(setLanguage());
        linearLayout.addView(btnConfig);
        return linearLayout;
    }

    private RelativeLayout setLanguage() {
        RelativeLayout layout = new RelativeLayout(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
        params.gravity = Gravity.CENTER;
        layout.setLayoutParams(params);

        //CheckBox
        CheckBox checkBox = new CheckBox(context);
        LayoutParams paramsCheckbox = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        checkBox.setLayoutParams(paramsCheckbox);
        checkBox.setChecked(false);
        checkBox.setText(context.getResources().getString(R.string.Remember_account));
        checkBox.setId(ID.setID("saveUser"));

        //Image Language
        ImageView imgLanguage = new ImageView(context);
        RelativeLayout.LayoutParams paramsImglanguage = new RelativeLayout.LayoutParams(40, 40);
        paramsImglanguage.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramsImglanguage.addRule(RelativeLayout.CENTER_VERTICAL);
        imgLanguage.setLayoutParams(paramsImglanguage);
        imgLanguage.setImageDrawable(context.getResources().getDrawable(R.drawable.image_language_vn));
        imgLanguage.setId(ID.setID("imgLanguage"));

        layout.addView(checkBox);
        layout.addView(imgLanguage);
        return layout;
    }

    private RelativeLayout setHelp() {
        RelativeLayout layout = new RelativeLayout(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (height * 0.2));
        layout.setId(ID.setID("setHelp"));
        layout.setLayoutParams(params);
        layout.addView(setHelpUI());
        return layout;
    }

    private LinearLayout setHelpUI() {
        LinearLayout layoutHelp = new LinearLayout(context);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, ID.getID("setHelp"));
        layoutHelp.setLayoutParams(params);
        layoutHelp.setOrientation(VERTICAL);
        layoutHelp.addView(forgotPass());
        layoutHelp.addView(setTvRegister());

        return layoutHelp;
    }

    private LinearLayout forgotPass() {
        LinearLayout layout = new LinearLayout(context);

        LayoutParams paramsForgotPassRoot = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsForgotPassRoot.gravity = Gravity.CENTER;
        layout.setOrientation(HORIZONTAL);
        layout.setLayoutParams(paramsForgotPassRoot);
        layout.setId(ID.setID("forgotPass"));

        //TextView ForgotPass
        TextView tvForgotPass = new TextView(context);
        LayoutParams paramsForgotPass = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvForgotPass.setLayoutParams(paramsForgotPass);
        paramsForgotPass.gravity = Gravity.CENTER_VERTICAL;
        tvForgotPass.setText(context.getResources().getString(R.string.txtForgotPassword));
        tvForgotPass.setTextColor(context.getResources().getColor(R.color.greenor));
        tvForgotPass.setId(ID.setID("tvForgotPass"));
        tvForgotPass.setOnClickListener(this);
        tvForgotPass.setTextSize(pxToDp(19));

        TextView tv = new TextView(context);
        tv.setLayoutParams(paramsForgotPassRoot);
        tv.setText(" | ");
        tv.setTextColor(context.getResources().getColor(R.color.black));

        //TextView ForgotPass
        TextView tvHelp = new TextView(context);
        tvHelp.setLayoutParams(paramsForgotPass);
        tvHelp.setText(context.getResources().getString(R.string.txtHelp));
        tvHelp.setTextColor(context.getResources().getColor(R.color.greenor));
        tvHelp.setId(ID.setID("tvHelp"));
        tvHelp.setTextSize(pxToDp(19));
        tvHelp.setOnClickListener(this);
        layout.addView(tvForgotPass);
        layout.addView(tv);
        layout.addView(tvHelp);

        return layout;
    }

    private LinearLayout setTvRegister() {
        LinearLayout layout = new LinearLayout(context);
        LayoutParams paramsRegister = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsRegister.gravity = Gravity.CENTER;

        layout.setOrientation(HORIZONTAL);
        layout.setLayoutParams(paramsRegister);

        TextView tv = new TextView(context);
        tv.setLayoutParams(paramsRegister);
        tv.setText(context.getResources().getString(R.string.don_t_have_an_account));
        tv.setTextColor(context.getResources().getColor(R.color.black));
        tv.setTextSize(pxToDp(19));


        //TextView Register
        TextView tvRegister = new TextView(context);
        tvRegister.setLayoutParams(paramsRegister);
        tvRegister.setText(context.getResources().getString(R.string.txtRegister));
        tvRegister.setId(ID.setID("tvRegister"));
        tvRegister.setOnClickListener(this);
        tvRegister.setTextColor(context.getResources().getColor(R.color.greenor));
        tvRegister.setTextSize(pxToDp(19));

        layout.addView(tv);
        layout.addView(tvRegister);
        return layout;
    }

    private static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == ID.getID("tvForgotPass")) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.fpts.com.vn/forgotpassword")));
        }
        if (view.getId() == ID.getID("tvRegister")) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://ezopen.fpts.com.vn/pages/default.aspx")));
        }
        if (view.getId() == ID.getID("tvHelp")) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.fpts.com.vn/help?s=14")));
        }
    }
}
