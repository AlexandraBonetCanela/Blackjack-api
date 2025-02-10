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
    public Mono<Game> save(Game game) {
        return gameMongoRepository.save(game);
    }

    @Override
    public Mono<Game> findById(String id) {
        return gameMongoRepository.findById(id);
    }

    @Override
    public Mono<Boolean> deleteById(String id) {
        return gameMongoRepository.deleteById(id).then(Mono.just(true));
    }
}
