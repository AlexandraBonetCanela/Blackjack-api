package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.ChangePlayerNameRequest;
import edu.alexandra.blackjack.domain.Player;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PlayerService {

    Mono<Player> getOrCreatePlayer(String playerName);

    Mono<Player> changePlayerName(UUID id, ChangePlayerNameRequest changePlayerNameRequest);

    Mono<Player> savePlayer(Player player);
}
