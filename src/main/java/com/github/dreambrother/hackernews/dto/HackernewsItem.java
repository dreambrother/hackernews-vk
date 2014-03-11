package com.github.dreambrother.hackernews.dto;

import javax.xml.bind.annotation.*;

@XmlType(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class HackernewsItem {

    @XmlElement(required = true)
    private String title;
    @XmlElement(required = true)
    private String link;
    @XmlElement(name = "comments", required = true)
    private String commentsLink;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCommentsLink() {
        return commentsLink;
    }

    public void setCommentsLink(String commentsLink) {
        this.commentsLink = commentsLink;
    }
}
