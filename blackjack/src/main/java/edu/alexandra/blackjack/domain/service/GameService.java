package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.domain.Game;
import reactor.core.publisher.Mono;

public interface GameService {

    Mono<Game> createGame(CreateGameRequest newGame);

    Mono<Game> getGame(Long id);
}
