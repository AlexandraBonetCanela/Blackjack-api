package edu.alexandra.blackjack.domain.repository;

import edu.alexandra.blackjack.domain.Player;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PlayerRepository {

    Mono<Player> findByName(String name);

    Mono<Player> save(Player player);

    Mono<Player> changePlayerName(UUID id, String playerNewName);
}
