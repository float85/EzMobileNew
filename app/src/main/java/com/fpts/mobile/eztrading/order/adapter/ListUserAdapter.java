package com.fpts.mobile.eztrading.order.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fpts.mobile.eztrading.R;

import java.util.List;


public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.MyviewHoder> {
    List<String> list;
    SetOnClickListenerRV setOnClickListenerRV;

    public void setClickRVListener(SetOnClickListenerRV setOnClickListenerRV) {
        this.setOnClickListenerRV = setOnClickListenerRV;
    }

    public ListUserAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ListUserAdapter.MyviewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ordertype, parent, false);
        return new MyviewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHoder holder, int position) {
        holder.btnType.setText(list.get(position));

        holder.btnType.setOnClickListener(v ->
                setOnClickListenerRV.OnClickTheLoai(list.get(position), position)
        );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHoder extends RecyclerView.ViewHolder {
        Button btnType;

        public MyviewHoder(View v) {
            super(v);
            btnType = v.findViewById(R.id.btnOrderType);
        }
    }
}
