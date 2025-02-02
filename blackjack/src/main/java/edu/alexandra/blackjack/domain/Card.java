package edu.alexandra.blackjack.domain;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Card {

    private String rank;
    private String suit;

}