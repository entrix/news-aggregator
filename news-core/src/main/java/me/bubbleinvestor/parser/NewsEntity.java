package me.bubbleinvestor.parser;

public class NewsEntity {

    private String title;

    private String desc;

    public NewsEntity(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }
}
