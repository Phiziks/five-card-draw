package com.ishmael.fivecarddraw;

import com.ishmael.fivecarddraw.dto.Card;
import com.ishmael.fivecarddraw.dto.Deck;
import com.ishmael.fivecarddraw.dto.Draw;
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
        PokerHand pokerHand = new PokerHand();

        deckService = new DeckServiceImp();
        cardService = new DrawServiceImp();
        rankService = new RankServiceImp(pokerHand);


        try {


            Deck deck = deckService.createNewDeck(1, false);

            if (!deck.isShuffled()) {
                deckService.shuffleDeck(deck);
                shuffleSimulation();
            } else {
                shuffleSimulation();
            }

            Draw draw = cardService.drawCards(deck, 5);
            loading = false;

            List<Card> cards = draw.getCards();
            displayHand(cards);
            System.out.println("You have : " + rankHand(cards));


        } catch (Exception e) {
            loading = false;
            throw new RuntimeException(e);
        }

    }

    private static boolean loading = true;

    private static synchronized void shuffleSimulation() {
        Thread th = new Thread(() -> {
            try {
                while (loading) {
                    System.out.print("shuffling....");
                    Thread.sleep(500);
                }
                loading = false;
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

    void  displayHand(List<Card> cards) {
        System.out.println();
        System.out.print("Your Hand: ");
        for (Card card : cards) {
            System.out.print(card.getValue() + card.getSuit().toString() + " ");
        }
        System.out.println();

    }


    @Bean
    public RestTemplate getRestTemplate() {

        return new RestTemplate();
    }

}
