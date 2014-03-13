package com.github.dreambrother.hackernews.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HackernewsItemList {

    @XmlElement
    private List<HackernewsItem> items;

    public List<HackernewsItem> getItems() {
        return items;
    }

    public void setItems(List<HackernewsItem> items) {
        this.items = items;
    }
}
