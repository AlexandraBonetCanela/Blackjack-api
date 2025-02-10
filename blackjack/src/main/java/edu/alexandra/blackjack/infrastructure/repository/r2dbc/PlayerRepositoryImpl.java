package edu.alexandra.blackjack.infrastructure.repository.r2dbc;

import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository {

    private final PlayerMySQLRepository playerMySQLRepository;

    @Override
    public Mono<Player> findById(String id) {
        return playerMySQLRepository.findById(id);
    }

    @Override
    public Mono<Player> findByName(String name) {
        return playerMySQLRepository.findByName(name);
    }

    @Override
    public Mono<Player> save(Player player) {
        return playerMySQLRepository.save(player);
    }

    @Override
    public Mono<Player> updatePlayerScore(String id, int score) {
        return playerMySQLRepository.updatePlayerScore(id, score);
    }

    @Override
    public Mono<Player> changePlayerName(String id, String newName) {
        return playerMySQLRepository.updatePlayerName(id, newName);
    }

    @Override
    public Flux<Player> findAllByOrderByTotalScoreDesc() {
        return playerMySQLRepository.findAllByOrderByTotalScoreDesc();
    }
}
