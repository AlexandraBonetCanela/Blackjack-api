package edu.alexandra.blackjack.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();

        List<String> rankType = List.of("ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King");
        List<String> suitType = List.of("CLUBS", "DIAMONDS", "HEARTS", "SPADES");
        for (String rank : rankType){
            for(String suit: suitType) {
                this.cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(this.cards);
    }

    public Card getCard() {
        return getCards().removeFirst();
    }
}
