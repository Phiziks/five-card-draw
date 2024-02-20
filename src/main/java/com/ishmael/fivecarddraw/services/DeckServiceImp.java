package com.ishmael.fivecarddraw.services;

import com.ishmael.fivecarddraw.enums.Suit;
import com.ishmael.fivecarddraw.enums.Value;
import com.ishmael.fivecarddraw.dto.Card;
import com.ishmael.fivecarddraw.dto.Deck;
import com.ishmael.fivecarddraw.interfaces.DeckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DeckServiceImp implements DeckService {


    @Override
    public Deck createNewDeck(int deckCount, boolean shuffle) {

        Deck deck = new Deck();

        List<Card> cardDeck = new ArrayList<>();

        //for each deck count, create 52 cards, based on the suit and values
        for (int i = 0; i < deckCount; i++) {

            //create a single deck of 52 cards
            for (Suit suit : Suit.values()) {
                for (Value face : Value.values()) {
                    Card card = new Card(face, suit);
                    cardDeck.add(card);
                }
            }
        }

        deck.setCards(cardDeck);
        deck.setRemaining(cardDeck.size());

        if (shuffle) {
            this.shuffleDeck(deck);
        }

        return deck;

    }

    @Override
    public void shuffleDeck(Deck deck) {
        Collections.shuffle(deck.getCards());
        deck.setShuffled(true);
    }

}
