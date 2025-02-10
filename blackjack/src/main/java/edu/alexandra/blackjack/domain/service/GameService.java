package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.application.rest.request.PlayGameRequest;
import edu.alexandra.blackjack.application.rest.response.GameResponse;
import reactor.core.publisher.Mono;


public interface GameService {

    Mono<GameResponse> createGame(CreateGameRequest newGame);

    Mono<GameResponse> getGame(String id);

    Mono<GameResponse> playGame(String id, PlayGameRequest move);

    Mono<Boolean> deleteGame(String id);
}
