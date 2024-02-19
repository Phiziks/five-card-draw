package com.ishmael.fivecarddraw.interfaces;

import com.ishmael.fivecarddraw.dto.Deck;
import com.ishmael.fivecarddraw.dto.RetrieveDeckRequest;

import java.util.Optional;

public interface DeckService {

    Optional<Deck> retrieveDeck(RetrieveDeckRequest retrieveDeckRequest);

    Deck shuffleDeck(String deckId);
}
