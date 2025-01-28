package edu.alexandra.blackjack.infrastructure.repository.r2dbc;

import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PlayerRepositoryImpl implements PlayerRepository {

    private final PlayerMySQLRepository playerMySQLRepository;

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
}
