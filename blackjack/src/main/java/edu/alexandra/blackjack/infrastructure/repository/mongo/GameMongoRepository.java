package edu.alexandra.blackjack.infrastructure.repository.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface GameMongoRepository extends ReactiveMongoRepository<GameEntity, String> {

    Mono<GameEntity> save(GameEntity gameEntity);

}
