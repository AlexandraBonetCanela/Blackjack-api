package edu.alexandra.blackjack.infrastructure.repository.mongo;

import edu.alexandra.blackjack.domain.Game;
import edu.alexandra.blackjack.domain.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {

    private final GameMongoRepository gameMongoRepository;

    @Override
    public Mono<Game> save(Game game) {
        return gameMongoRepository.save(GameEntity.toEntity(game))
                .map(GameEntity::toDomain);
    }

    @Override
    public Mono<Game> findById(UUID id) {
        return gameMongoRepository.findById(id.toString())
                .map(GameEntity::toDomain);
    }

    @Override
    public Mono<Boolean> deleteById(UUID id) {
        return gameMongoRepository.deleteById(id.toString()).then(Mono.just(true));
    }
}
