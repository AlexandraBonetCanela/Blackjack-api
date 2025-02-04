package edu.alexandra.blackjack.infrastructure.repository.mongo;

import edu.alexandra.blackjack.domain.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Document(collection = "games")
public class GameEntity {

    @Id
    private String id;

    private Player player;

    private Deck deck;

    private List<Card> playerHand;

    private List<Card> dealerHand;

    private BigDecimal moneyBet;

    private GameStatus status;

    private GameResult gameResult;

    public static Game toDomain(GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId() != null?
                        UUID.fromString(gameEntity.getId())
                        : null)
                .status(gameEntity.getStatus())
                .deck(gameEntity.getDeck())
                .dealerHand(gameEntity.getDealerHand())
                .playerHand(gameEntity.getPlayerHand())
                .moneyBet(gameEntity.getMoneyBet())
                .player(gameEntity.getPlayer())
                .gameResult(gameEntity.getGameResult())
                .build();
    }

    public static GameEntity toEntity(Game game){
        return GameEntity.builder()
                .id(game.getId() != null? game.getId().toString() : null)
                .status(game.getStatus())
                .deck(game.getDeck())
                .dealerHand(game.getDealerHand())
                .playerHand(game.getPlayerHand())
                .moneyBet(game.getMoneyBet())
                .player(game.getPlayer())
                .gameResult(game.getGameResult())
                .build();
    }
}