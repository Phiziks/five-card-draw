package com.ishmael.fivecarddraw;

import com.ishmael.fivecarddraw.dto.Card;
import com.ishmael.fivecarddraw.dto.Deck;
import com.ishmael.fivecarddraw.interfaces.DeckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DeckServiceImpTests {

    @Autowired
    private DeckService deckService;

    @Test
    void singleDeckSizeTest() {
        Deck deck = deckService.createNewDeck(1, false);
        assertEquals(52, deck.getCards().size());
    }

    @Test
    void doubleDeckSizeTest() {
        Deck deck = deckService.createNewDeck(2, false);
        assertEquals(104, deck.getCards().size());
    }

    @Test
    void testThatDeckIsNotShuffledWhenCreated() {
        Deck deck = deckService.createNewDeck(1, false);
        assertEquals(false, deck.isShuffled());
    }

    @Test
    void testDeckShuffledUponCreation() {
        Deck deck = deckService.createNewDeck(1, true);
        assertEquals(true, deck.isShuffled());
    }

    @Test
    void testShufflingOfDeck() {
        Deck deck = deckService.createNewDeck(1, false);
        deckService.shuffleDeck(deck);
        assertEquals(true, deck.isShuffled());
    }

    @Test
    void testDeckGetsShuffled() {
        Deck deck = deckService.createNewDeck(1, false);

        List<Card> unshuffledDeck = new ArrayList<>(deck.getCards());

        deckService.shuffleDeck(deck);
        boolean isSame = unshuffledDeck.equals(deck.getCards());
        assertEquals(false, isSame);

    }


}
