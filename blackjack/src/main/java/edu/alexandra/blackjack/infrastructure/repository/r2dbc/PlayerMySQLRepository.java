package edu.alexandra.blackjack.infrastructure.repository.r2dbc;

import edu.alexandra.blackjack.domain.Player;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface PlayerMySQLRepository extends R2dbcRepository<PlayerEntity, String> {

    Mono<PlayerEntity> findById(String id);

    Mono<PlayerEntity> findByName(String playerName);

    Mono<PlayerEntity> save(PlayerEntity playerEntity);

    @Modifying
    @Query("UPDATE player SET name = :newName WHERE id = :id")
    Mono<Integer> updatePlayerName(String id, String newName);

    Flux<Player> findAllByOrderByTotalScoreDesc();
}
