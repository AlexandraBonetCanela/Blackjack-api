package edu.alexandra.blackjack.domain.repository;

import edu.alexandra.blackjack.domain.Game;
import reactor.core.publisher.Mono;

public interface GameRepository {

    Mono<Game> createGame(Game game);
}
