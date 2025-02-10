package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.ChangePlayerNameRequest;
import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.domain.exception.PlayerNotFoundException;
import edu.alexandra.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> getOrCreatePlayer(String name) {

        return playerRepository.findByName(name)
                .switchIfEmpty(
                        playerRepository.save(
                                Player.builder()
                                .id(UUID.randomUUID())
                                .name(name)
                                .totalScore(BigDecimal.ZERO)
                                .build()
                        )
                                .thenMany(playerRepository.findByName(name).repeat(2))
                                .next()
                );
    }

    @Override
    public Mono<Player> changePlayerName(UUID id, ChangePlayerNameRequest changePlayerNameRequest) {
        return playerRepository.changePlayerName(id, changePlayerNameRequest.getPlayerNewName())
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(id)));
    }

    @Override
    public Mono<Player> savePlayer(Player player) {
        return playerRepository.save(player);
    }
}
