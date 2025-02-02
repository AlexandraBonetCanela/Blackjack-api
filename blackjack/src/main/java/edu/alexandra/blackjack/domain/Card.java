package edu.alexandra.blackjack.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Card {

    private String rank;
    private String suit;

    Integer getCardValue(Card card) {
        return null;
    }
}



