package com.ishmael.fivecarddraw.dto;

import lombok.Data;

import java.util.List;

@Data
public class Draw {
    boolean success;
    Deck deck;
    List<Card> cards;
    int remaining;
}
