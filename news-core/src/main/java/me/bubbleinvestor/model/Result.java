package me.bubbleinvestor.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by SBT-Volkov-AV on 02.02.2018.
 */
@Builder
@ToString
public class Result {

    @Getter
    @Setter
    private Boolean result;
}
