package com.ishmael.fivecarddraw.services;

import com.ishmael.fivecarddraw.dto.Draw;
import com.ishmael.fivecarddraw.interfaces.DrawService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class DrawServiceImp implements DrawService {

    private final RestTemplate restTemplate;

    public DrawServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Draw> drawCards(String deckId, String cardCount) {

        Map<String, String> params = new HashMap<>();
        params.put("deck_id", deckId);
        params.put("count", cardCount);


        Draw draw = restTemplate.getForObject(buildRetrieveDeckUrl(deckId, cardCount), Draw.class, params);

        if (draw == null) {
            return Optional.empty();
        } else {
            return Optional.of(draw);
        }
    }


    private String buildRetrieveDeckUrl(String deckId, String cardCount) {
        return String.format("https://www.deckofcardsapi.com/api/deck/%s/draw/?count=%s", deckId, cardCount);
    }

}
