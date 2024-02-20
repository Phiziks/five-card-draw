package com.ishmael.fivecarddraw;

import com.ishmael.fivecarddraw.enums.Suit;
import com.ishmael.fivecarddraw.enums.Value;
import com.ishmael.fivecarddraw.dto.Card;
import com.ishmael.fivecarddraw.interfaces.RankService;
import com.ishmael.fivecarddraw.services.PokerHand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class RankServiceImpTests {

	@MockBean
	private PokerHand pokerHand;

	@Autowired
	private RankService rankService;

	@Test
	void testRankServiceImplementation(){

		//rankService = new RankServiceImp(pokerHand);
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Value.ACE, Suit.HEARTS));
		cards.add(new Card(Value.JACK,Suit.HEARTS));
		cards.add(new Card(Value.ACE,Suit.HEARTS));
		cards.add(new Card(Value.FOUR,Suit.CLUBS));
		cards.add(new Card(Value.KING,Suit.DIAMONDS));

		when(pokerHand.rankCards()).thenReturn("High Card");

		String result = rankService.rank(cards);

		assertEquals("High Card", result);
		verify(pokerHand, times(1)).setPokerHand(anyString());

	}




}
