package edu.alexandra.blackjack.infrastructure.repository.mongo;

import edu.alexandra.blackjack.domain.Game;
import edu.alexandra.blackjack.domain.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class GameRepositoryImpl implements GameRepository {

    private final GameMongoRepository gameMongoRepository;

    @Override
    public Mono<Game> createGame(Game game) {
        return gameMongoRepository.save(GameEntity.toEntity(game))
                .map(GameEntity::toDomain);
    }

    @Override
    public Mono<Game> findById(Long id) {
        return gameMongoRepository.findById(id.toString())
                .map(GameEntity::toDomain);
    }
}
