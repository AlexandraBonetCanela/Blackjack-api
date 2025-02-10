package edu.alexandra.blackjack.domain.repository;

import edu.alexandra.blackjack.domain.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;;


public interface PlayerRepository {

    Mono<Player> findById(String id);

    Mono<Player> findByName(String name);

    Mono<Player> save(Player player);

    Mono<Player> updatePlayerScore(String id, int score);

    Mono<Player> changePlayerName(String id, String playerNewName);

    Flux<Player> findAllByOrderByTotalScoreDesc();
}
