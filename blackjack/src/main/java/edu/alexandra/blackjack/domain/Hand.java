package edu.alexandra.blackjack.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Hand {

    private List<Card> cards;

}
