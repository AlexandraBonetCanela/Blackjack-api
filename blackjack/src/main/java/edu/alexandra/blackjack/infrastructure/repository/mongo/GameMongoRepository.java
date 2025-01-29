package edu.alexandra.blackjack.infrastructure.repository.mongo;

import edu.alexandra.blackjack.domain.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface GameMongoRepository extends ReactiveMongoRepository<GameEntity, String> {

    Mono<GameEntity> save(GameEntity gameEntity);
}
