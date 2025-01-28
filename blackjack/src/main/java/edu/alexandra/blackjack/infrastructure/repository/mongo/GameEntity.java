package edu.alexandra.blackjack.infrastructure.repository.mongo;

import edu.alexandra.blackjack.domain.Game;
import edu.alexandra.blackjack.domain.GameStatus;
import edu.alexandra.blackjack.domain.Player;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private GameStatus status;

    public static Game toDomain(GameEntity gameEntity) {
        return Game.builder()
                .id(gameEntity.getId() != null? Long.valueOf(gameEntity.getId()) : null)
                .status(gameEntity.getStatus())
                .player(gameEntity.getPlayer())
                .build();
    }

    public static GameEntity toEntity(Game game){
        return GameEntity.builder()
                .id(game.getId() != null? game.getId().toString() : null)
                .status(game.getStatus())
                .player(game.getPlayer())
                .build();
    }
}