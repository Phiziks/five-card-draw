package com.ishmael.fivecarddraw;

import com.ishmael.fivecarddraw.dto.Card;
import com.ishmael.fivecarddraw.dto.Deck;
import com.ishmael.fivecarddraw.dto.Draw;
import com.ishmael.fivecarddraw.dto.RetrieveDeckRequest;
import com.ishmael.fivecarddraw.interfaces.DeckService;
import com.ishmael.fivecarddraw.interfaces.DrawService;
import com.ishmael.fivecarddraw.interfaces.RankService;
import com.ishmael.fivecarddraw.services.DeckServiceImp;
import com.ishmael.fivecarddraw.services.DrawServiceImp;
import com.ishmael.fivecarddraw.services.PokerHand;
import com.ishmael.fivecarddraw.services.RankServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Slf4j
public class FiveCardDrawApplication implements CommandLineRunner {

    DeckService deckService;

    DrawService cardService;

    RankService rankService;



    public static void main(String[] args) {
        SpringApplication.run(FiveCardDrawApplication.class, args);
    }


    @Override
    public void run(String... args) throws IOException, InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        PokerHand pokerHand = new PokerHand();

        deckService = new DeckServiceImp(restTemplate);
        cardService = new DrawServiceImp(restTemplate);
        rankService = new RankServiceImp(restTemplate,pokerHand);

        RetrieveDeckRequest retrieveDeckRequest = new RetrieveDeckRequest();

        try {
            loading();
            retrieveDeckRequest.setCount(1);
            Optional<Deck> deck = deckService.retrieveDeck(retrieveDeckRequest);
            if (deck.isPresent()) {
                Optional<Draw> draw = cardService.drawCards(deck.get().getDeckId(), "5");
                loading = false;
                if (draw.isPresent()) {
                    List<Card> cards = draw.get().getCards();
                    displayHand(cards);
                    System.out.println("You have : " + rankHand(cards));

                }
            }


        } catch (Exception e) {
            loading = false;
            throw new RuntimeException(e);
        }

    }

    private static boolean loading = true;

    private static synchronized void loading() {
        Thread th = new Thread(() -> {
            try {
                while (loading) {
                    System.out.print("shuffling....");
                    Thread.sleep(500);
                }
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        });
        th.start();

    }

    private String rankHand(List<Card> cards) {
        return rankService.rank(cards);
    }

    void displayHand(List<Card> cards) {
        System.out.println();
        System.out.print("Your Hand: ");
        for (Card card : cards) {
            System.out.print(formatCardValueDisplay(card.getValue()) + formatCardSuitDisplay(card.getSuit()) + " ");
        }
        System.out.println();

    }

    String formatCardValueDisplay(String value) {
        return value.substring(0, 1);
    }

    String formatCardSuitDisplay(String suit) {


        switch (suit) {
            case "CLUBS" -> suit = "\u2663";
            case "SPADES" -> suit = "\u2660";
            case "HEARTS" -> suit = "\u2665";
            case "DIAMONDS" -> suit = "\u2666";
            default -> log.info("could not determine the conversion");
        }

        return suit;

    }

    @Bean
    public RestTemplate getRestTemplate() {

        return new RestTemplate();
    }

}
