package me.bubbleinvestor.parser;

import org.junit.Test;

public class CoinTelegraphTest {

    @Test
    public void mainPageTest() {
        CoinTelegraphParser parser = new CoinTelegraphParser();
        for (NewsEntity entity: parser.getNewsFeed()) {
            System.out.println(entity.getTitle() + " -> " + entity.getDesc());
        }
    }
}
