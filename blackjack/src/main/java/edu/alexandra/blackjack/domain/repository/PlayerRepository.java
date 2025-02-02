package edu.alexandra.blackjack.domain.repository;

import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.infrastructure.repository.r2dbc.PlayerEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PlayerRepository {

    Mono<Player> findById(UUID id);

    Mono<Player> findByName(String name);

    Mono<Player> save(Player player);

    Mono<Player> changePlayerName(UUID id, String playerNewName);
}
