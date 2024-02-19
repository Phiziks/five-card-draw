package com.ishmael.fivecarddraw.dto;

import lombok.Data;

@Data
public class DrawCardsRequest {
    String deckId;
    int numOfCards;
}
