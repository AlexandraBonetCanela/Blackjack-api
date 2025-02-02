package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.application.rest.request.PlayGameRequest;
import edu.alexandra.blackjack.application.rest.response.GameResponse;
import edu.alexandra.blackjack.domain.Game;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GameService {

    Mono<GameResponse> createGame(CreateGameRequest newGame);

    Mono<GameResponse> getGame(UUID id);

    Mono<GameResponse> playGame(UUID id, PlayGameRequest move);

    Mono<Boolean> deleteGame(UUID id);
}
