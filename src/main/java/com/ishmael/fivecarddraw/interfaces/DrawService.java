package com.ishmael.fivecarddraw.interfaces;

import com.ishmael.fivecarddraw.dto.Deck;
import com.ishmael.fivecarddraw.dto.Draw;

public interface DrawService {

    Draw drawCards(Deck deck, int cardCount);
}
