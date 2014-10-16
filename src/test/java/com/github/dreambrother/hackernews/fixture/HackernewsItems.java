package com.github.dreambrother.hackernews.fixture;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.ArrayList;
import java.util.List;

public final class HackernewsItems {

    private HackernewsItems() {}

    public static List<HackernewsItem> twoItems() {
        List<HackernewsItem> items = new ArrayList<>();

        HackernewsItem itemOne = new HackernewsItem();
        itemOne.setId(1L);
        itemOne.setTitle("Item One");
        itemOne.setUrl("http://item.one");
        itemOne.setScore(100);
        items.add(itemOne);

        HackernewsItem itemTwo = new HackernewsItem();
        itemTwo.setId(2L);
        itemTwo.setTitle("Item two");
        itemTwo.setUrl("http://item.two");
        itemTwo.setScore(30);
        items.add(itemTwo);

        return items;
    }

    public static List<HackernewsItem> threeItems() {
        List<HackernewsItem> items = new ArrayList<>(twoItems());

        HackernewsItem item = new HackernewsItem();
        item.setId(3L);
        item.setTitle("Item Three");
        item.setUrl("http://item.three");
        item.setScore(150);
        items.add(item);

        return items;
    }

    public static HackernewsItem oneItem() {
        return twoItems().get(0);
    }
}
