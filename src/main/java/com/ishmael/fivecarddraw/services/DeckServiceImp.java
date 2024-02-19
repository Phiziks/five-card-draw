package com.ishmael.fivecarddraw.services;

import com.ishmael.fivecarddraw.dto.Deck;
import com.ishmael.fivecarddraw.dto.RetrieveDeckRequest;
import com.ishmael.fivecarddraw.interfaces.DeckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class DeckServiceImp implements DeckService {

    private final RestTemplate restTemplate;

    public DeckServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Deck> retrieveDeck(RetrieveDeckRequest retrieveDeckRequest) {

        Map<String, String> params = Collections.singletonMap("count", String.valueOf(retrieveDeckRequest.getCount()));
        Deck deck = restTemplate.getForObject(buildRetrieveDeckUrl(), Deck.class, params);


        if (deck == null) {
            return Optional.empty();
        } else {

            return Optional.of(deck);
        }

    }


    @Override
    public Deck shuffleDeck(String deckId) {
        return null;
    }

    private String buildRetrieveDeckUrl() {
        return "https://www.deckofcardsapi.com/api/deck/new/shuffle";
    }
}
