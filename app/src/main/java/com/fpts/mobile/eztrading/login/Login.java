package com.fpts.mobile.eztrading.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fpts.mobile.eztrading.common.ID;
import com.fpts.mobile.eztrading.common.SettingApp;
import com.fpts.mobile.eztrading.main.MainActivity;

import mobile.fpts.com.privatefpts.IPrivate;
import mobile.fpts.com.privatefpts.LoginPresenter;
import mobile.fpts.com.privatefpts.NetworkPresenter;
import mobile.fpts.com.privatefpts.UpdatePresenter;

import static mobile.fpts.com.privatefpts.util.MethodUtils.initConfirmDialog;

public class Login extends AppCompatActivity implements IPrivate.ICheckUpdate, IPrivate.INetworkView, IPrivate.ILogin {
    UiLogin uiLogin;
    SettingApp settingApp;
    ViewHoder hoder;
    private static final int REQUEST_SETTINGS = 99;
    NetworkPresenter networkPresenter;
    UpdatePresenter updatePresenter;
    LoginPresenter loginPresenter;
    public final static String WS_URL = "https://eztrade.fpts.com.vn/Gateway5/ezGateway.asmx";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiLogin = new UiLogin(this);
        setContentView(uiLogin);
        hideNAVIGATION();
        networkPresenter = new NetworkPresenter(this, this);
        loginPresenter = new LoginPresenter(this, this, WS_URL);
        hoder = new ViewHoder();
    }


    @Override
    public void onLoginSucscess(String mes) {
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }

    @Override
    public void onLoginFail(String mes) {
        Toast.makeText(this, mes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUI() {
        hoder.btnConfig.setOnClickListener(view ->
                loginPresenter.setLogin(hoder.etUserName.getText().toString(),
                        hoder.etPassWord.getText().toString(), hoder.checkBox.isChecked()));
        hoder.etUserName.setOnClickListener(v -> {
            hoder.etUserName.setText("058C");
            hoder.etUserName.setSelection(4);
        });
        hoder.imgLanguage.setOnClickListener(view -> {
            settingApp = new SettingApp(this);
            startActivity(new Intent(this, LoginActivity.class));
        });
        hoder.tvForgotPass.setMovementMethod(LinkMovementMethod.getInstance());
        hoder.tvHelp.setMovementMethod(LinkMovementMethod.getInstance());
        hoder.tvRegister.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void isConnected(boolean connected) {
        if (connected) {
            updatePresenter = new UpdatePresenter(this, this, WS_URL);
        } else {
            initConfirmDialog(this, "Kiểm tra lại kết nối mạng",
                    (dialog, which) -> startActivityForResult(new Intent(Settings.ACTION_SETTINGS), REQUEST_SETTINGS)).show();
        }
    }

    @Override
    public void onNoUpdate() {
        loginPresenter.setAutoLogin();
    }

    public void hideNAVIGATION() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = this.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private class ViewHoder {
        TextView tvRegister, tvHelp, tvForgotPass;
        EditText etUserName, etPassWord;
        CheckBox checkBox;
        Button btnConfig;
        ImageView imgLanguage;

        public ViewHoder() {
            etUserName = findViewById(ID.getID("username"));
            etPassWord = findViewById(ID.getID("password"));
            checkBox = findViewById(ID.getID("saveUser"));
            btnConfig = findViewById(ID.getID("btnConfig"));
            tvRegister = findViewById(ID.getID("tvRegister"));
            tvHelp = findViewById(ID.getID("tvHelp"));
            tvForgotPass = findViewById(ID.getID("tvForgotPass"));
            imgLanguage = findViewById(ID.getID("imgLanguage"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (networkPresenter != null)
            networkPresenter.onDestroy();
    }
}
