package com.fpts.mobile.eztrading.watchlistSearch;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.databinding.ItemWatchlistSearchRecentBinding;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.RecyclerViewHolder> {
    private List<String> listData;
    ItemWatchlistSearchRecentBinding binding;

    public SearchAdapter(List<String> listData) {
        this.listData = listData;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.item_watchlist_search_recent, parent, false);
        View itemview = binding.getRoot();
        return new RecyclerViewHolder(itemview, binding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (!listData.get(position).equals("")) {
            holder.binding.txtMaCk.setText(listData.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.w("SearchAdapter", "onClick: " + listData.get(position));
//                    listener.onClick(listData.get(position));
                }
            });
            holder.binding.img.setImageResource(R.drawable.ic_banggia_search_recent_dark);

//            if (getWhiteBlackFormSPR()) {
//                holder.binding.txtMaCk.setTextColor(App.getInstance().getResources().getColor(R.color.black));
//                holder.binding.img.setImageResource(R.drawable.ic_banggia_search_recent);
//            } else {
//                holder.binding.txtMaCk.setTextColor(App.getInstance().getResources().getColor(R.color.white));
//                holder.binding.img.setImageResource(R.drawable.ic_banggia_search_recent_white);
//            }
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ItemWatchlistSearchRecentBinding binding;

        public RecyclerViewHolder(View itemView, ItemWatchlistSearchRecentBinding binding) {
            super(itemView);
            this.binding = binding;
        }
    }

}
