package com.fpts.mobile.eztrading.order;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.databinding.MarproFagmentBinding;
import com.fpts.mobile.eztrading.order.adapter.ListUserAdapter;

import java.util.List;

import mobile.fpts.com.privatefpts.IPrivate;
import mobile.fpts.com.privatefpts.OrderMarProPresenter;
import mobile.fpts.com.privatefpts.model.ClientBalanceDetails;
import mobile.fpts.com.privatefpts.model.StockQuote;
import mobile.fpts.com.privatefpts.util.MethodUtils;

import static com.fpts.mobile.eztrading.login.LoginActivity.WS_URL;


public class OrderMarPro extends Fragment implements View.OnTouchListener, IPrivate.IOrder {
    boolean symboCheck = true;
    MarproFagmentBinding binding;
    ListUserAdapter listUserAdapter;
    OrderMarProPresenter orderMarProPresenter;
    StockQuote stockQuotenew;

    public static OrderMarPro newInstance() {
        return new OrderMarPro();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.marpro_fagment, container, false);

        orderMarProPresenter = new OrderMarProPresenter(this, getContext(), WS_URL);
        start();
        return binding.getRoot();
    }

    private void start() {
        stockQuotenew = new StockQuote();
        binding.btnSymbol.setOnClickListener((View v) -> ClearUI());

        binding.etTradingSymbol.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if (!hasFocus) {
                if (symboCheck) {
                    orderMarProPresenter.getSymbolsDetail(binding.etTradingSymbol.getText().toString().toUpperCase());
                    binding.etTradingSymbol.setText(binding.etTradingSymbol.getText().toString().toUpperCase());
                    binding.progressBar.setVisibility(View.VISIBLE);
                }
                ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(binding.etTradingSymbol.getWindowToken(), 0);
                symboCheck = false;
            }
        });

        binding.etTradingSymbol.setOnClickListener((View view) -> symboCheck = true);

        binding.etVol.addTextChangedListener(textWatcherQuantity);

        binding.etVol.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if (!hasFocus)
                orderMarProPresenter.checkVol(binding.etVol.getText().toString(), binding.tvNameCompany.getText().toString());
        });

        binding.etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.etPass.getText().toString().length() > 0) {
                    binding.btnConfig.setFocusable(true);
                    binding.btnConfig.setClickable(true);
                    binding.btnConfig.setTextColor(getContext().getResources().getColor(R.color.black));

                } else {
                    binding.btnConfig.setFocusable(false);
                    binding.btnConfig.setClickable(false);
                    binding.btnConfig.setTextColor(getContext().getResources().getColor(R.color.colorHintBtn));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void updateUI(StockQuote stockQuote, ClientBalanceDetails clientBalanceDetails, List<String> listOrderType) {

        binding.progressBar.setVisibility(View.GONE);
        binding.tvFocusablePrice.setVisibility(View.GONE);
        binding.tvFocusableQuantity.setVisibility(View.GONE);
        binding.tvFocusablePass.setVisibility(View.GONE);
        binding.vCashBuy.setVisibility(View.VISIBLE);

        listUserAdapter = new ListUserAdapter(listOrderType);
        binding.rvOrderType.setAdapter(listUserAdapter);

        binding.tvMoneyBuying.setText(" " + MethodUtils.formatNumber(clientBalanceDetails.getSMTH()));
        binding.tvRemainingCreditLine.setText(" " + MethodUtils.formatNumber(clientBalanceDetails.getRemainingQuota()));
        binding.tvCeil.setText(stockQuote.getCeiling());
        binding.tvFloor.setText(stockQuote.getFloor());
        binding.tvRefPrice.setText(stockQuote.getReference());
        binding.tvBuy1.setText(stockQuote.getBuy1());
        binding.tvSell1.setText(stockQuote.getSell1());
        binding.tvMatching.setText(stockQuote.getMatch());
        binding.etVol.setText("");
        binding.etPass.setText("");
        binding.etPrice.setText("");
        if (!stockQuote.getRateMar().equals("0"))
            binding.tvTLV.setText("(" + stockQuote.getRateMar() + "%)");
        else binding.tvTLV.setText("(TLV)");
        binding.tvMaxVol.setText("(" + "Max " + stockQuote.getVolMax() + ")");

        binding.tvNameCompany.setText(stockQuote.getExchange() + " - " + " Công ty cổ phần chứng khoán FPT");
        binding.tvCashBuy.setText("0");

        binding.tvMaxVol.setOnClickListener((View v) ->
                binding.etVol.setText(stockQuote.getVolMax()));

        binding.tvCeil.setOnClickListener((View v) -> {
            binding.tvMaxVol.setText("(" + "Max " + MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getCeiling(), MethodUtils.MARPRO) + ")");
            stockQuote.setVolMax(MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getCeiling(), MethodUtils.MARPRO));
            binding.etPrice.setText(stockQuote.getCeiling());
            binding.tvFocusablePrice.setVisibility(View.GONE);
            binding.tvCashBuy.setText(MethodUtils.getCashBuy(binding.etVol.getText().toString(), stockQuote.getRateMar(),
                    stockQuote.getCeiling(), 3
            ));
        });
        binding.tvFloor.setOnClickListener((View v) -> {
            binding.etPrice.setText(stockQuote.getFloor());
            stockQuote.setVolMax(MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getFloor(), MethodUtils.MARPRO));
            binding.tvFocusablePrice.setVisibility(View.GONE);
            binding.tvMaxVol.setText("(" + "Max " + MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getFloor(), MethodUtils.MARPRO) + ")");
            binding.tvCashBuy.setText(MethodUtils.getCashBuy(binding.etVol.getText().toString(), stockQuote.getRateMar(),
                    stockQuote.getFloor(), 3
            ));
        });
        binding.tvRefPrice.setOnClickListener((View v) -> {
            binding.etPrice.setText(stockQuote.getReference());
            binding.tvFocusablePrice.setVisibility(View.GONE);
            stockQuote.setVolMax(MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getReference(), MethodUtils.MARPRO));
            binding.tvMaxVol.setText("(" + "Max " + MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getReference(), MethodUtils.MARPRO) + ")");
            binding.tvCashBuy.setText(MethodUtils.getCashBuy(binding.etVol.getText().toString(), stockQuote.getRateMar(),
                    stockQuote.getReference(), 3
            ));
        });
        binding.tvBuy1.setOnClickListener((View v) -> {
            binding.etPrice.setText(stockQuote.getBuy1());
            binding.tvFocusablePrice.setVisibility(View.GONE);
            stockQuote.setVolMax(MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getBuy1(), MethodUtils.MARPRO));
            binding.tvMaxVol.setText("(" + "Max " + MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getBuy1(), MethodUtils.MARPRO) + ")");
            binding.tvCashBuy.setText(MethodUtils.getCashBuy(binding.etVol.getText().toString(), stockQuote.getRateMar(),
                    stockQuote.getBuy1(), 3
            ));
        });
        binding.tvSell1.setOnClickListener((View v) -> {
            binding.etPrice.setText(stockQuote.getSell1());
            binding.tvFocusablePrice.setVisibility(View.GONE);
            stockQuote.setVolMax(MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getSell1(), MethodUtils.MARPRO));
            binding.tvMaxVol.setText("(" + "Max " + MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getSell1(), MethodUtils.MARPRO) + ")");
            binding.tvCashBuy.setText(MethodUtils.getCashBuy(binding.etVol.getText().toString(), stockQuote.getRateMar(),
                    stockQuote.getSell1(), 3
            ));
        });
        binding.tvMatching.setOnClickListener((View v) -> {
            binding.etPrice.setText(stockQuote.getMatch());
            binding.tvFocusablePrice.setVisibility(View.GONE);
            stockQuote.setVolMax(MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getMatch(), MethodUtils.MARPRO));
            binding.tvMaxVol.setText("(" + "Max " + MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getMatch(), MethodUtils.MARPRO) + ")");
            binding.tvCashBuy.setText(MethodUtils.getCashBuy(binding.etVol.getText().toString(), stockQuote.getRateMar(),
                    stockQuote.getMatch(), 3
            ));
        });
        binding.imgNextQuantity.setOnClickListener((View view) -> binding.etVol.setText(MethodUtils.setNextQuantity(binding.etVol.getText().toString(),
                binding.etPrice.getText().toString(),
                stockQuote.getVolMax(),
                binding.tvNameCompany.getText().toString()
        )));
        binding.imgBackQuantity.setOnClickListener((View view) -> binding.etVol.setText(MethodUtils.setBackQuantity(binding.etVol.getText().toString(),
                binding.tvNameCompany.getText().toString()
        )));
        binding.imgNextPrice.setOnClickListener((View view) -> {
            stockQuotenew = MethodUtils.setNextPrice(getContext(), stockQuote, binding.etPrice.getText().toString(), 3, binding.etVol.getText().toString());
            binding.etPrice.setText(stockQuotenew.getNewPrice());
            binding.tvCashBuy.setText(stockQuotenew.getCashBuy());
        });
        binding.imgBackPrice.setOnClickListener((View view) -> {
            stockQuotenew = MethodUtils.setBackPrice(getContext(), stockQuote, binding.etPrice.getText().toString(), 3, binding.etVol.getText().toString());
            binding.etPrice.setText(stockQuotenew.getNewPrice());
            binding.tvCashBuy.setText(stockQuotenew.getCashBuy());
        });

        listUserAdapter.setClickRVListener((String name, int position) -> {
            if (name.equals("LO")) {
                binding.etPrice.setText("");
                stockQuote.setVolMax(MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getCeiling(), MethodUtils.MARPRO));
                binding.tvMaxVol.setText("(" + "Max " + MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getCeiling(), MethodUtils.MARPRO) + ")");
                binding.tvFocusablePrice.setVisibility(View.GONE);
            } else {
                binding.etPrice.setText(name);
                binding.tvMaxVol.setText("(" + "Max " + MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getCeiling(), MethodUtils.MARPRO) + ")");
                binding.tvFocusablePrice.setVisibility(View.VISIBLE);
                stockQuote.setVolMax(MethodUtils.setVolMax(stockQuote, clientBalanceDetails, stockQuote.getCeiling(), MethodUtils.MARPRO));
            }
        });
    }

    @Override
    public void ClearUI() {
        binding.progressBar.setVisibility(View.GONE);
        binding.tvFocusablePrice.setVisibility(View.VISIBLE);
        binding.tvFocusableQuantity.setVisibility(View.VISIBLE);
        binding.tvFocusablePass.setVisibility(View.VISIBLE);
        binding.vCashBuy.setVisibility(View.GONE);

        binding.tvMoneyBuying.setText("");
        binding.tvRemainingCreditLine.setText("");
        binding.tvCeil.setText("");
        binding.tvFloor.setText("");
        binding.tvRefPrice.setText("");
        binding.tvBuy1.setText("");
        binding.tvSell1.setText("");
        binding.tvMatching.setText("");
        binding.tvTLV.setText("(TLV)");
        binding.tvMaxVol.setText("(" + "Max " + ")");
        binding.tvNameCompany.setText("");
        binding.etTradingSymbol.setText("");
        binding.etVol.setText("");
        binding.etPass.setText("");
        binding.etPrice.setText("");

        binding.tvMaxVol.setOnClickListener((View v) -> binding.etVol.setText(""));
        binding.tvCeil.setOnClickListener((View v) -> binding.etPrice.setText(""));
        binding.tvFloor.setOnClickListener((View v) -> binding.etPrice.setText(""));
        binding.tvRefPrice.setOnClickListener((View v) -> binding.etPrice.setText(""));
        binding.tvBuy1.setOnClickListener((View v) -> binding.etPrice.setText(""));
        binding.tvSell1.setOnClickListener((View v) -> binding.etPrice.setText(""));
        binding.tvMatching.setOnClickListener((View v) -> binding.etPrice.setText(""));

        binding.rvOrderType.setAdapter(null);
    }

    @Override
    public boolean onTouch(View v, MotionEvent ev) {

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
        return false;
    }

    @Override
    public void OrderSucscess() {

    }

    @Override
    public void OrderError(String mes) {

    }

    private TextWatcher textWatcherQuantity = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            binding.etVol.removeTextChangedListener(textWatcherQuantity);
            if (binding.etVol.toString().trim().length() > 0) {
                binding.etVol.setText(MethodUtils.formatNumber(binding.etVol.getText().toString().trim().replace(",", "")));
            } else binding.etVol.setText("");
            binding.etVol.addTextChangedListener(textWatcherQuantity);
            binding.etVol.setSelection(binding.etVol.getText().toString().length());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
