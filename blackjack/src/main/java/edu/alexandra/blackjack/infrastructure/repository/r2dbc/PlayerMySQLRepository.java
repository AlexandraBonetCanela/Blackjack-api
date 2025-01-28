package edu.alexandra.blackjack.infrastructure.repository.r2dbc;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PlayerMySQLRepository extends R2dbcRepository<PlayerEntity, Long> {

    Mono<PlayerEntity> findByName(String playerName);

    Mono<PlayerEntity> save(PlayerEntity playerEntity);
}
