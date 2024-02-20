package com.ishmael.fivecarddraw.services;

import com.ishmael.fivecarddraw.dto.Card;
import com.ishmael.fivecarddraw.dto.Deck;
import com.ishmael.fivecarddraw.dto.Draw;
import com.ishmael.fivecarddraw.interfaces.DrawService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class DrawServiceImp implements DrawService {

    @Override
    public Draw drawCards(Deck deck, int cardCount) {

        List<Card> drawnCards = new ArrayList<>();

        List<Card> cards = deck.getCards();

        for (int i = 0; i < cardCount; i++) {
            Card card = cards.get(getRandomCard(cards.size()));
            drawnCards.add(card);
            cards.remove(card);

        }

        deck.setRemaining(cards.size());
        Draw draw = new Draw();
        draw.setCards(drawnCards);
        draw.setDeck(deck);
        draw.setRemaining(cards.size());
        return draw;

    }

    int getRandomCard(int max) {
        return ThreadLocalRandom.current().nextInt(0, max)+1;
    }
}
