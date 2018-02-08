package me.bubbleinvestor.model;

import io.searchbox.annotations.JestId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by SBT-Volkov-AV on 26.01.2018.
 */
@Builder
@ToString
public class NewsEntity {

    public NewsEntity() {
    }

    public NewsEntity(String id, String name, String description, Date datetime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.datetime = datetime;
    }

    @JestId
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Date datetime;

}
