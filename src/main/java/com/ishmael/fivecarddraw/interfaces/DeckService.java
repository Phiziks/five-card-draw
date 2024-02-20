package com.ishmael.fivecarddraw.interfaces;

import com.ishmael.fivecarddraw.dto.Deck;

public interface DeckService {

    Deck createNewDeck(int count, boolean shuffle);

    void shuffleDeck(Deck deck);
}
