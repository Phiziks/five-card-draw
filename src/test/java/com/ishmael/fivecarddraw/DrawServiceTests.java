package com.ishmael.fivecarddraw;

import com.ishmael.fivecarddraw.dto.Deck;
import com.ishmael.fivecarddraw.dto.Draw;
import com.ishmael.fivecarddraw.interfaces.DeckService;
import com.ishmael.fivecarddraw.interfaces.DrawService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DrawServiceTests {

    @Autowired
    DrawService drawService;

    @Autowired
    DeckService deckService;

    @Test
    void testCardsDecreaseFromDeckWhenFiveCardsDrawn() {
        Deck deck = deckService.createNewDeck(1,true);
        Draw draw = drawService.drawCards(deck,5);

        assertEquals(47,draw.getRemaining());
    }


}
