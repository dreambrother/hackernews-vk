package com.github.dreambrother.hackernews.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class HackernewsHtmlUtils {

    public static Map<String, Integer> parseRating(String html) {
        Document document = Jsoup.parse(html);
        Elements trs = getNewsTRs(document);

        Map<String, Integer> result = new HashMap<>();
        int sizeWithoutFooterElements = trs.size() - 3;
        for (int i = 0; i < sizeWithoutFooterElements; i++) {
            // title
            Element titleElement = trs.get(i++).select(".title a").get(0); // should be only 1
            String title = titleElement.html();

            // raiting
            Elements foundRating = trs.get(i++).select(".subtext span");
            Integer raiting;
            if (foundRating.isEmpty()) {
                // zero raiting?
                raiting = 0;
            } else {
                Element raitingElement = foundRating.get(0); // should be only 1
                String raitingString = raitingElement.html();
                raiting = parseRaitingFromString(raitingString);
            }

           result.put(title, raiting);
        }

        return result;
    }

    private static Elements getNewsTRs(Document document) {
        Elements result = document.select("table table tr");
        result.remove(0); // remove 'Login' element
        return result;
    }

    private static Integer parseRaitingFromString(String raitingString) {
        String numberString = raitingString.split(" ")[0];
        return Integer.valueOf(numberString);
    }
}
