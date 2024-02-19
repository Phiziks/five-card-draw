package com.ishmael.fivecarddraw.services;

import com.ishmael.fivecarddraw.dto.Deck;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ShuffleServiceImpl {

    private final RestTemplate restTemplate;

    public ShuffleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    Optional<Deck> shuffle(String deckCount) {
        Map<String, String> params = new HashMap<>();
        params.put("count", deckCount);
        Deck deck = restTemplate.getForObject(buildRetrieveDeckUrl(), Deck.class, params);
        if (deck != null) {
            return Optional.of(deck);
        }

        return Optional.empty();


    }

    private String buildRetrieveDeckUrl() {
        return "https://www.deckofcardsapi.com/api/deck/new/shuffle";
    }
}
