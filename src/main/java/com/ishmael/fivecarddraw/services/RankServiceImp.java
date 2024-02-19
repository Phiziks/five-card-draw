package com.ishmael.fivecarddraw.services;

import com.ishmael.fivecarddraw.dto.Card;
import com.ishmael.fivecarddraw.interfaces.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class RankServiceImp implements RankService {

    private final RestTemplate restTemplate;

    private final PokerHand pokerHand;

    public RankServiceImp(RestTemplate restTemplate, PokerHand pokerhand) {
        this.restTemplate = restTemplate;
        this.pokerHand = pokerhand;

    }

    @Override
    public String rank(List<Card> cards) {
        StringBuilder s = new StringBuilder();
        for (Card card : cards) {
            s.append(formatCards(card.getValue())).append(card.getSuit().charAt(0)).append(" ");
        }

        pokerHand.setPokerHand(s.toString());
        return "your hand is:" + pokerHand.rankCards();
    }

    String formatCards(String card) {

        if (card.equals("10")) {
            return "T";
        } else if (card.equalsIgnoreCase("ACE")) {
            return "A";
        }else if (card.equalsIgnoreCase("QUEEN")){
            return "Q";
        }else if (card.equalsIgnoreCase("KING")){
            return "K";
        }else if (card.equalsIgnoreCase("JACK")){
            return "J";
        }
        return card;
    }


}
