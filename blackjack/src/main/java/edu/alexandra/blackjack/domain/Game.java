package edu.alexandra.blackjack.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Game {

    private final UUID id;
    private final Player player;

    @Builder.Default
    private Deck deck = new Deck();

    @Builder.Default
    private List<Card> playerHand = new ArrayList<>();

    @Builder.Default
    private List<Card> dealerHand = new ArrayList<>();

    private final BigDecimal moneyBet;
    private GameStatus status;
    private GameResult gameResult;

    public Game dealInitialCards(){

        if (deck.getCards().size() >= 4) {
            return this.toBuilder()
                    .playerHand(List.of(deck.getCard(), deck.getCard()))
                    .dealerHand(List.of(deck.getCard(), deck.getCard()))
                    .build();
        }
        return this;
    }

    public static int getCardScore(Card card){

        return switch (card.getRank()) {
            case "Jack", "Queen", "King" -> 10;
            case "ACE" -> 1;
            default -> Integer.parseInt(card.getRank());
        };
    }

    public static int getHandScore(List<Card> hand) {

        boolean aceIsPresent = false;
        int total = 0;

        for (Card card: hand){
            if (card.getRank().equals("ACE")) {
                aceIsPresent = true;
            }
            total += getCardScore(card);
        }

        if (aceIsPresent && ((total + 10) <= 21)) {
                total += 10;
        }
        return total;
    }

    public Mono<Game> executeGameLogic(MoveType move){

        // higher than the dealer's
        // Max 21

        switch (move){
            case MoveType.STAND :
                stand();
                break;
            case MoveType.HIT:
                hit();
                break;
        }
        return Mono.just(this);
    }

    public void stand(){

        int playerScore = getHandScore(playerHand);
        int dealerScore = getHandScore(dealerHand);

        if (playerScore > dealerScore) {
            gameResult = GameResult.WON;
        } else if (playerScore < dealerScore) {
            gameResult = GameResult.LOST;
        } else {
            gameResult = GameResult.DRAW;
        }
        finishGame();
    }

    public void hit(){

        playerHand.add(deck.getCard());

        int playerScore = getHandScore(playerHand);
        if (playerScore == 21 ){
            gameResult = GameResult.WON;
            finishGame();
            return;
        }
        if (playerScore > 21) {
            gameResult = GameResult.BUST;
            finishGame();
            return;
        }

        int dealerScore = getHandScore(dealerHand);
        if (dealerScore < 17) {
            dealerHand.add(deck.getCard());
            dealerScore = getHandScore(dealerHand);

            if (dealerScore == 21) {
                gameResult = GameResult.LOST;
                finishGame();
                return;
            }
            
            if (dealerScore > 21) {
                gameResult = GameResult.WON;
                finishGame();
            }
        }
    }

    public void finishGame(){
        status = GameStatus.FINISHED;
    }
}
