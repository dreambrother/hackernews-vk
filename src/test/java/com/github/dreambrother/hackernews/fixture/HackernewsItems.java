package com.github.dreambrother.hackernews.fixture;

import com.github.dreambrother.hackernews.dto.HackernewsItem;

import java.util.ArrayList;
import java.util.List;

public final class HackernewsItems {

    private HackernewsItems() {}

    public static List<HackernewsItem> twoItems() {
        List<HackernewsItem> items = new ArrayList<>();

        HackernewsItem itemOne = new HackernewsItem();
        itemOne.setTitle("Item One");
        itemOne.setLink("http://item.one");
        itemOne.setCommentsLink("http://item.one/comments");
        items.add(itemOne);

        HackernewsItem itemTwo = new HackernewsItem();
        itemTwo.setTitle("Item two");
        itemTwo.setLink("http://item.two");
        itemTwo.setCommentsLink("http://item.two/comments");
        items.add(itemTwo);

        return items;
    }

    public static List<HackernewsItem> threeItems() {
        List<HackernewsItem> items = new ArrayList<>(twoItems());

        HackernewsItem item = new HackernewsItem();
        item.setTitle("Item Three");
        item.setLink("http://item.three");
        item.setCommentsLink("http://item.three/comments");
        items.add(item);

        return items;
    }
}
