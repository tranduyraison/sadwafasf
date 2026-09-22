package com.example.myapplication;

import java.io.Serializable;

public class Article implements Serializable {
    private String title;
    private String content;
    private int coverImageResId;
    private int viewCount;

    public Article(String title, String content, int coverImageResId) {
        this.title = title;
        this.content = content;
        this.coverImageResId = coverImageResId;
        this.viewCount = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCoverImageResId() {
        return coverImageResId;
    }

    public void setCoverImageResId(int coverImageResId) {
        this.coverImageResId = coverImageResId;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }
}
