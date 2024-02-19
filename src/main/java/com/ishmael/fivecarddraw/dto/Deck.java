package com.ishmael.fivecarddraw.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Deck {

    boolean success;

    @JsonProperty("deck_id")
    String deckId;
    boolean shuffled;
    int remaining;
}


