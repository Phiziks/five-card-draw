package com.ishmael.fivecarddraw.interfaces;

import com.ishmael.fivecarddraw.dto.Draw;

import java.util.Optional;

public interface DrawService {

    Optional<Draw> drawCards(String deckId, String cardCount);
}
