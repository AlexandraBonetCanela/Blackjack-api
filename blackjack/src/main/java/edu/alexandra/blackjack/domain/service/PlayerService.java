package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.ChangePlayerNameRequest;
import edu.alexandra.blackjack.domain.Player;
import reactor.core.publisher.Mono;


public interface PlayerService {

    Mono<Player> getOrCreatePlayer(String playerName);

    Mono<Player> changePlayerName(String id, ChangePlayerNameRequest changePlayerNameRequest);

    Mono<Player> updatePlayerScore(Player player);
}
