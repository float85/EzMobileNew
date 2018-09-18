package com.fpts.mobile.eztrading.newsDetail;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.fpts.mobile.eztrading.R;
import com.fpts.mobile.eztrading.common.HttpHandler;
import com.fpts.mobile.eztrading.databinding.FragmentNewsDetailBinding;
import com.fpts.mobile.eztrading.news.DataNews;
import com.fpts.mobile.eztrading.news.NewsArticle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class NewsDetailFragment extends Fragment {
    private static String TAG = "NewsDetailFragment";
    private static final String ARG_NEWSID = "ARG_NEWSID";
    private static final String ARG_SYMBOL = "ARG_SYMBOL";
    private static final String ARG_STYPE = "ARG_SYMBOL";
    private FragmentNewsDetailBinding detailBinding;
    private String newsID = "";
    private String symbol = "";
    private String stype = "";
    protected int width = Resources.getSystem().getDisplayMetrics().widthPixels / 5;
    protected int height = width;
    protected int heightItem = height * 2 / 3;

    public static NewsDetailFragment newInstance(String newsID, String symbol, String stype) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NEWSID, newsID);
        args.putString(ARG_SYMBOL, symbol);
        args.putString(ARG_STYPE, stype);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newsID = getArguments().getString(ARG_NEWSID);
            symbol = getArguments().getString(ARG_SYMBOL);
            stype = getArguments().getString(ARG_STYPE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        App.setPosition(Define.TYPE_MENU_DETAIL_PAGE);
//        App.setTypeFragment(Define.TYPE_MENU_DETAIL_PAGE);
//        MainActivity mainActivity = (MainActivity) getActivity();
//        mainActivity.setBackDetail();
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_detail, container, false);
        return detailBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.news_details));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("");

        splitChiTietTin(newsID);
        onDisplayRelated();
    }

    private void splitChiTietTin(String newsID1) {
        new Thread(() -> {
            String get = new HttpHandler().makeServiceCall(DataNewsDetail.getLinkJson(newsID1));

            if (get == null) {
                String[] item = new String[0];
            } else {
                String body = get.replace("face=arial", "")
                        .replace("size=2", "")
                        .replace("size=3", "")
                        .replace("color=#000000", "")
                        .replace("color=#015396", "");

                Log.w("NewDetailPresenter", "splitChiTietTin: " + body);
                Document document = Jsoup.parse(body);
                document.body();

                String link = "";
                if (document.body().getElementsByTag("a").size() > 0) {
                    link = document.body().getElementsByAttribute("href").attr("href")
                            .replace("</b></font>", "");
                }
                //document.body().getElementsByTag("a").remove().toString()
                String item[] = body.split("</b></font></P>");

                String finalLink = link;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (item.length > 2) {
                            onDisplay(item[0], item[1].replace("Download File", "") + item[2], finalLink);
//                         "<html><body style=\"text-align:justify\">" + item[0] + "</body></Html>",
                        } else if (item.length > 1) {
                            onDisplay(item[0], item[1].replace("Download File", ""), finalLink);
                        } else {
                            onDisplay(item[0], "", finalLink);
                        }
                    }
                });
            }
        }).start();
    }

    public void onDisplay(String title, String body, String link) {
        Log.w("NewsDetailFragment", "onDisplay: " + link);
//
        detailBinding.imgNewsDetail.setImageResource(R.drawable.icon_app);
        detailBinding.wvTitle.setText(Html.fromHtml(title));
        detailBinding.wvBody.setText(Html.fromHtml(body));

        if (link != null && link != "") {
            detailBinding.wvOpenfile.setVisibility(android.view.View.VISIBLE);
            detailBinding.wvOpenfile.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
            });
        }
    }

    public void onDisplayRelated() {
        ArrayList<NewsArticle> list = DataNews.getCache(getContext());
        Log.w("NewsDetailFragment", "onDisplayRelated: ");
        RelatedNewsDetailAdapter adapter = new RelatedNewsDetailAdapter(list, getContext());
        detailBinding.relativeLayoutRelativedNews.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        detailBinding.tvTitleTinlienquan.setVisibility(android.view.View.VISIBLE);

        adapter.setOnItemClickListener(new RelatedNewsDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                moveFragment(list.get(position).getNewsId());
//                Log.d("thunghiem", position + "av");
            }
        });
    }

    public void moveFragment(String id) {
        this.newsID = id;
//        detailBinding.llDetail.setVisibility(android.view.View.GONE);

        splitChiTietTin(id);
        //dua len tren dau
        detailBinding.scrollviewNewsDetail.scrollTo(0, 0);
        detailBinding.scrollviewNewsDetail.getParent().requestChildFocus(
                detailBinding.scrollviewNewsDetail, detailBinding.scrollviewNewsDetail);
        detailBinding.scrollviewNewsDetail.fullScroll(ScrollView.FOCUS_UP);
        detailBinding.scrollviewNewsDetail.smoothScrollTo(0, 0);
    }
}
