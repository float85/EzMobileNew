package com.fpts.mobile.eztrading.news;

public class NewsArticle {
    private String NewsId;
    private String NewsTitle;
    private String NewsDate;
    private String NewsSizeInByte;
    private String NewsSizeInKB;
    private String NewsDate2;
    private String NewsFTitle;
    private String NewsContent;
    private String NewsImg;

    public NewsArticle() {
    }

    public NewsArticle(String newsId, String newsTitle, String newsDate, String newsSizeInByte,
                       String newsSizeInKB, String newsDate2, String newsFTitle, String newsContent,
                       String newsImg) {
        NewsId = newsId;
        NewsTitle = newsTitle;
        NewsDate = newsDate;
        NewsSizeInByte = newsSizeInByte;
        NewsSizeInKB = newsSizeInKB;
        NewsDate2 = newsDate2;
        NewsFTitle = newsFTitle;
        NewsContent = newsContent;
        NewsImg = newsImg;
    }

    public String getNewsId() {
        return NewsId;
    }

    public void setNewsId(String newsId) {
        NewsId = newsId;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
    }

    public String getNewsDate() {
        return NewsDate;
    }

    public void setNewsDate(String newsDate) {
        NewsDate = newsDate;
    }

    public String getNewsSizeInByte() {
        return NewsSizeInByte;
    }

    public void setNewsSizeInByte(String newsSizeInByte) {
        NewsSizeInByte = newsSizeInByte;
    }

    public String getNewsSizeInKB() {
        return NewsSizeInKB;
    }

    public void setNewsSizeInKB(String newsSizeInKB) {
        NewsSizeInKB = newsSizeInKB;
    }

    public String getNewsDate2() {
        return NewsDate2;
    }

    public void setNewsDate2(String newsDate2) {
        NewsDate2 = newsDate2;
    }

    public String getNewsFTitle() {
        return NewsFTitle;
    }

    public void setNewsFTitle(String newsFTitle) {
        NewsFTitle = newsFTitle;
    }

    public String getNewsContent() {
        return NewsContent;
    }

    public void setNewsContent(String newsContent) {
        NewsContent = newsContent;
    }

    public String getNewsImg() {
        return NewsImg;
    }

    public void setNewsImg(String newsImg) {
        NewsImg = newsImg;
    }
}
