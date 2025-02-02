package edu.alexandra.blackjack.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Game {

    private final UUID id;
    private final Player player;
    private Deck deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;
    private final BigDecimal moneyBet;
    private GameStatus status;

    public void dealInitialCards(){

        if(this.deck == null){
            this.deck = new Deck();
        }

        if (this.dealerHand == null){
            this.dealerHand = new ArrayList<>();
        }

        if (this.playerHand == null){
            this.playerHand = new ArrayList<>();
        }

        if (deck.getCards().size() >= 4) {
            playerHand.add(deck.getCard());
            dealerHand.add(deck.getCard());
            playerHand.add(deck.getCard());
            dealerHand.add(deck.getCard());
        }
    }
}
