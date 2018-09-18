package com.fpts.mobile.eztrading.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Toast;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.SettingApp;
import com.fpts.mobile.eztrading.databinding.ActivityLoginBinding;
import com.fpts.mobile.eztrading.main.MainActivity;

import mobile.fpts.com.privatefpts.IPrivate;
import mobile.fpts.com.privatefpts.LoginPresenter;
import mobile.fpts.com.privatefpts.NetworkPresenter;
import mobile.fpts.com.privatefpts.UpdatePresenter;


public class LoginActivity extends AppCompatActivity implements IPrivate.ICheckUpdate, IPrivate.INetworkView, IPrivate.ILogin {

    ActivityLoginBinding binding;
    SettingApp settingApp;
    private static final int REQUEST_SETTINGS = 99;
    NetworkPresenter networkPresenter;
    UpdatePresenter updatePresenter;
    LoginPresenter loginPresenter;
    public final static String WS_URL = "https://eztrade.fpts.com.vn/Gateway5/ezGateway.asmx";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        networkPresenter = new NetworkPresenter(this, this);
        loginPresenter=new LoginPresenter(this,this,WS_URL);
        hideNAVIGATION();
    }

    public void setLanguage(View v) {
        settingApp = new SettingApp(this);
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void updateUI() {
        binding.edUserName.setOnClickListener(v -> {
            binding.edUserName.setText("058C");
            binding.edUserName.setSelection(4);
        });
        binding.tvForgotPassword.setMovementMethod(LinkMovementMethod.getInstance());
        binding.tvHelp.setMovementMethod(LinkMovementMethod.getInstance());
        binding.tvRegister.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setLogin(View v) {
        loginPresenter.setLogin(binding.edUserName.getText().toString()
                , binding.edPassword.getText().toString(), binding.checkbox.isChecked());
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

    public static AlertDialog initConfirmDialog(Context context, String message, DialogInterface.OnClickListener confirmClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Thông Báo")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Đồng ý", confirmClick);
        return builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (networkPresenter != null)
            networkPresenter.onDestroy();
    }
}