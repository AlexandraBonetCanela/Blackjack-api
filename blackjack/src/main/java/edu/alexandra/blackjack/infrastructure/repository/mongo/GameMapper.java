package edu.alexandra.blackjack.infrastructure.repository.mongo;

import edu.alexandra.blackjack.domain.Game;

public class GameMapper {

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
