package edu.alexandra.blackjack.domain.repository;

import edu.alexandra.blackjack.domain.Game;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GameRepository {

    Mono<Game> save(Game game);

    Mono<Game> findById(UUID id);

    Mono<Boolean> deleteById(UUID id);
}
