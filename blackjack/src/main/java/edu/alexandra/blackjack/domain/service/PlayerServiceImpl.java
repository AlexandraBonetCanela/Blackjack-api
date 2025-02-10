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
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    @Override
    public Mono<Player> getOrCreatePlayer(String name) {
        return playerRepository.findByName(name)
                .switchIfEmpty(
                        Mono.defer(() -> {
                            String playerId = UUID.randomUUID().toString();
                            System.out.println("🆕 Creating new player: " + name + " with ID: " + playerId);

                            return playerRepository.save(
                                            Player.builder()
                                                    .name(name)
                                                    .totalScore(BigDecimal.ZERO)
                                                    .build()
                                    )
                                    .flatMap(savedPlayer -> {
                                        System.out.println("✅ Player successfully saved: " + savedPlayer);

                                        // Ensure MySQL has committed the new player before returning it
                                        return playerRepository.findByName(savedPlayer.getName())
                                                .doOnNext(player -> System.out.println("🔄 Retrieved Player with ID: " + player.getId()));
                                    });
                        })
                )
                .doOnNext(player -> System.out.println("🔄 Final Player Retrieved: " + player));
    }


    @Override
    public Mono<Player> changePlayerName(String id, ChangePlayerNameRequest changePlayerNameRequest) {
        return playerRepository.changePlayerName(id, changePlayerNameRequest.getPlayerNewName())
                .then(playerRepository.findById(id))
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(id)));
    }

    @Override
    public Mono<Player> updatePlayerScore(Player player) {
        return playerRepository.updatePlayerScore(player.getId(), player.getTotalScore().intValue());
    }
}
