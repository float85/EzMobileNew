package com.fpts.mobile.eztrading.newsDetail;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.news.NewsArticle;

import java.util.ArrayList;
import java.util.List;


import static android.content.Context.MODE_PRIVATE;

public class RelatedNewsDetailAdapter extends RecyclerView.Adapter<RelatedNewsDetailAdapter.MyViewHolder> {
    private ArrayList<NewsArticle> newList;
    Context context;
    OnItemClickListener onItemClickListener;

    public RelatedNewsDetailAdapter(ArrayList<NewsArticle> newList, Context context) {
        this.newList = newList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tin_lien_quan, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(newList.get(position).getNewsTitle());

//        //Check theme white
//        SharedPreferences shared = App.getInstance().getSharedPreferences(Define.SHARED_PREFRENCES_APP, MODE_PRIVATE);
//        boolean isLight = (shared.getBoolean(Define.SHARED_PREFRENCES_MODE_THEME, true));
        //Xet mau tab layout
//        holder.tvTitle.setTextColor(isLight ? App.getInstance().getResources().getColor(R.color.black) :
//                context.getResources().getColor(R.color.white));

        // TODO: TamHV 6/28/2018 tách ngày 
        String date = "[" + newList.get(position).getNewsDate().split("\\s+")[0] + "]";
        holder.tvDate.setText(date);

        holder.imgNews.setImageResource(R.drawable.icon_app);
//        if (newList.get(position).getNewsImg().equals("")) {
//            holder.imgNews.setImageResource(R.drawable.icon_app);
//        } else {
//            loadImg(holder.imgNews, newList.get(position).getNewsImg());
//        }

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("thunghiem", position + "thanh cong");
//            }
//        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNews;
        TextView tvTitle, tvDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.imgNews);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    interface OnItemClickListener{
        public void onItemClick(View v, int position);
    }
}
