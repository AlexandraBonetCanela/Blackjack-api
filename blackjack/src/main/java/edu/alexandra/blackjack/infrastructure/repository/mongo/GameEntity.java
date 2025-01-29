package edu.alexandra.blackjack.infrastructure.repository.mongo;

import edu.alexandra.blackjack.domain.Game;
import edu.alexandra.blackjack.domain.GameStatus;
import edu.alexandra.blackjack.domain.Player;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

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

    private BigDecimal moneyBet;

    private GameStatus status;

    public static Game toDomain(GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId() != null && gameEntity.getId().matches("\\d+")?
                        Long.valueOf(gameEntity.getId())
                        : null)
                .status(gameEntity.getStatus())
                .moneyBet(gameEntity.getMoneyBet())
                .player(gameEntity.getPlayer())
                .build();
    }

    public static GameEntity toEntity(Game game){
        return GameEntity.builder()
                .id(game.getId() != null? game.getId().toString() : null)
                .status(game.getStatus())
                .moneyBet(game.getMoneyBet())
                .player(game.getPlayer())
                .build();
    }
}