package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> getOrCreatePlayer(String name) {

        return playerRepository.findByName(name)
                .switchIfEmpty(
                        playerRepository.save(Player.builder()
                                .name(name)
                                .totalScore(BigDecimal.ZERO)
                                .build()
                        )
                );
    }
}
