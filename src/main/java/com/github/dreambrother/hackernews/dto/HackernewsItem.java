package com.github.dreambrother.hackernews.dto;

public class HackernewsItem {

    private Long id;
    private String title;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommentsLink() {
        throw new UnsupportedOperationException();
    }
}
