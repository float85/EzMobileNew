package com.fpts.mobile.eztrading.watchlistSearch;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.ValueApp;
import com.fpts.mobile.eztrading.databinding.FragmentWatchlistSearchBinding;
import com.fpts.mobile.eztrading.home.HomeFragment;
import com.fpts.mobile.eztrading.watchlist.DataWatchList;

import java.util.ArrayList;
import java.util.List;

public class WatchlistSearchFragment extends Fragment {
    FragmentWatchlistSearchBinding binding;
//    private ContentMainBinding mainBinding;
//    SearchAdapter adapter;
    View view;
    AutoCompleteTextView autoCompleteTextViewtest1;
    String[] listCompany;
    ArrayList<CustomerAutoComleteTextView> arrayList;

    SearchAdapter adapter;
    List<String> list;

    public static WatchlistSearchFragment newInstance() {
        WatchlistSearchFragment fragment = new WatchlistSearchFragment();
        Bundle bundle = new Bundle();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_watchlist_search, container, false);
        view = binding.getRoot();

        if (getArguments() != null) {
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ValueApp.contextSearch = getContext();
        ValueApp.activitySearch = getActivity();
        ValueApp.fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        onDisplay();
        autoCompleteTextViewtest1 = view.findViewById(R.id.edMaCk);
        loadDataOfAutoComleteTextView();

        binding.btnBack.setOnClickListener(v -> {
            ValueApp.fragmentTransaction.replace(R.id.linear_layout, HomeFragment.newInstance());
            ValueApp.fragmentTransaction.commit();
            ValueApp.vt.equals(ValueApp.ENUM_VT.FROM_MAIN);
        });
    }

    public void loadDataOfAutoComleteTextView() {
        ArrayList<String> arrayList1 = DataStock.getCodeStock(ValueApp.contextSearch);

        arrayList = new ArrayList<>();
        for (int i = 0; i < arrayList1.size(); i = i + 4) {
            try {
                arrayList.add(new CustomerAutoComleteTextView(arrayList1.get(i), arrayList1.get(i + 1),
                        arrayList1.get(i + 2), arrayList1.get(i + 3)));
            } catch (Exception e) {

            }
        }

        if (arrayList.size() == 0) {
            return;
        }

        binding.edMaCk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                AutoCompleteTextView AutoCompleteTextViewtest = view.findViewById(R.id.edMaCk);
                if (AutoCompleteTextViewtest.isPerformingCompletion()) {
                    return;
                }
                if (String.valueOf(s).compareToIgnoreCase("") == 0) {
                    return;
                } else {
                    try {
                        autoCompleteTextViewtest1.setThreshold(1);
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < arrayList.size(); i++) {
                            String strStockCode = arrayList.get(i).getStock_code() + " - " +
                                    arrayList.get(i).getPost_to() + " - " +
                                    arrayList.get(i).getName_vn();

                            if (arrayList.get(i).getStock_code().toLowerCase().startsWith(s.toString().toLowerCase())) {
                                list.add(strStockCode);
                            }
                        }

                        listCompany = new String[list.size()];
                        list.toArray(listCompany);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                R.layout.item_watchlist_search_recyclerview, listCompany);
                        autoCompleteTextViewtest1.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } catch (Exception e) {

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        autoCompleteTextViewtest1.setOnItemClickListener((parent, view, position, id) -> {
            binding.edMaCk.setText("");

            Log.d("thunghiem", listCompany[position]);
            String stock = listCompany[position].split("-")[0].trim();
            String san = listCompany[position].split("-")[1].trim();

            Log.d("thunghiem", stock);
            Log.d("thunghiem", san);

            DataWatchList.addCodeMaket(ValueApp.contextSearch, stock, san);

            ValueApp.fragmentTransaction.replace(R.id.linear_layout, HomeFragment.newInstance());
            ValueApp.fragmentTransaction.commit();
            ValueApp.vt.equals(ValueApp.ENUM_VT.FROM_MAIN);

            DataStock.addCodeReCent(ValueApp.contextSearch, listCompany[position]);
//            list.add(0, listCompany[position]);
//            adapter.notifyDataSetChanged();
        });
    }

    public void onDisplay() {
//        adapter = new SearchAdapter(list, new IListener() {
//            @Override
//            public void onClick(String symbol) {
//                setFragment(DetailCodeFragment.newInstance(symbol, false));
//            }
//        });
        list = DataStock.getCodeReCent(ValueApp.contextSearch);
        adapter = new SearchAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerSeachRecent.setLayoutManager(layoutManager);
        binding.recyclerSeachRecent.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
