package com.ishmael.fivecarddraw.services;

import com.ishmael.fivecarddraw.dto.Card;
import com.ishmael.fivecarddraw.interfaces.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RankServiceImp implements RankService {


    private final PokerHand pokerHand;

    public RankServiceImp(PokerHand pokerhand) {
        this.pokerHand = pokerhand;

    }

    @Override
    public String rank(List<Card> cards) {
        StringBuilder s = new StringBuilder();
        for (Card card : cards) {
            s.append(formatCards(card.getValue().toString())).append(card.getSuit().toString()).append(" ");
        }

        pokerHand.setPokerHand(s.toString());
        return pokerHand.rankCards();
    }

    private String formatCards(String card) {

        if (card.equals("10")) {
            return "T";
        }
        return card;
    }


}
