package com.ishmael.fivecarddraw.dto;

import lombok.Data;

import java.util.List;

@Data
public class Deck {

    boolean success;
    String deckId;
    boolean shuffled;
    List<Card> cards;
    int remaining;
}


