package me.bubbleinvestor.model;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoinTelegraphParser {

    private static String START_URL = "https://cointelegraph.com/tags/bitcoin";

    private Document document;

    public CoinTelegraphParser() {
        try {
            document = Jsoup.connect(START_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<NewsEntity> getNewsFeed() {
        List<NewsEntity> entityList = new ArrayList<>();
        Elements elements = document.getElementsByClass("col-sm-8");
        if (elements.size() > 0) {
            for (Element element: elements) {
                String title = null;
                String description = null;
                Elements elems = element.getElementsByTag("h2");
                if (elems.size() > 0) {
                    Element e = elems.get(0);
                    title = e.child(0).childNode(0).outerHtml();
                }
                elems = element.getElementsByTag("p");
                if (elems.size() > 0) {
                    Element e = elems.get(0);
                    description = e.childNode(1).childNode(0).outerHtml();
                }
                if (title != null && description != null) {
                    entityList.add(new NewsEntity(title, description));
                }
            }
        }
        return entityList;
    }

}
