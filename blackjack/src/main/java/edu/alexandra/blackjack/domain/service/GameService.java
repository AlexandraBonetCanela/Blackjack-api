package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.application.rest.request.PlayGameRequest;
import edu.alexandra.blackjack.domain.Game;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GameService {

    Mono<Game> createGame(CreateGameRequest newGame);

    Mono<Game> getGame(UUID id);

    Mono<Game> playGame(PlayGameRequest move);

    Mono<Boolean> deleteGame(UUID id);
}
