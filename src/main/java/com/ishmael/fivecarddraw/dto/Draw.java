package com.ishmael.fivecarddraw.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Draw {
    boolean success;
    @JsonProperty("deck_id")
    String deckId;
    List<Card> cards;
    int remaining;
}
