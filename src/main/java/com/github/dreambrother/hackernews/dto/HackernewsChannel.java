package com.github.dreambrother.hackernews.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlType(name = "channel")
@XmlAccessorType(XmlAccessType.FIELD)
public class HackernewsChannel {

    @XmlElement(name = "item")
    private List<HackernewsItem> items;

    public List<HackernewsItem> getItems() {
        return items;
    }

    public void setItems(List<HackernewsItem> items) {
        this.items = items;
    }
}
