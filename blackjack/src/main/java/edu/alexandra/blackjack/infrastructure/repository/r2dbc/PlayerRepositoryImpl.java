package edu.alexandra.blackjack.infrastructure.repository.r2dbc;

import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository {

    private final PlayerMySQLRepository playerMySQLRepository;

    @Override
    public Mono<Player> findById(UUID id) {
        return playerMySQLRepository.findById(id.toString())
                .map(PlayerEntity::toDomain);
    }

    @Override
    public Mono<Player> findByName(String name) {
        return playerMySQLRepository.findByName(name)
                .map(PlayerEntity::toDomain);
    }

    @Override
    public Mono<Player> save(Player player) {
        return playerMySQLRepository.save(PlayerEntity.toEntity(player))
                .map(PlayerEntity::toDomain);
    }

    @Override
    public Mono<Player> changePlayerName(UUID id, String newName) {
        return playerMySQLRepository.updatePlayerName(id.toString(), newName)
                .filter(rowsUpdated -> rowsUpdated > 0)
                .flatMap(rows -> playerMySQLRepository.findById(id.toString()))
                .map(PlayerEntity::toDomain);
    }

    @Override
    public Flux<Player> findAllByOrderByTotalScoreDesc() {
        return playerMySQLRepository.findAllByOrderByTotalScoreDesc();
    }
}
