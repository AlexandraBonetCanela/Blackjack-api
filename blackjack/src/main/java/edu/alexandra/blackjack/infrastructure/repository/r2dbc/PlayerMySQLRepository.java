package edu.alexandra.blackjack.infrastructure.repository.r2dbc;

import edu.alexandra.blackjack.domain.Player;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface PlayerMySQLRepository extends R2dbcRepository<Player, String> {

    Mono<Player> findById(String id);

    Mono<Player> findByName(String playerName);

    Mono<Player> save(Player player);

    @Modifying
    @Query("UPDATE player SET total_score = :score WHERE id = :id")
    Mono<Player> updatePlayerScore(String id, int score);

    @Modifying
    @Query("UPDATE player SET name = :newName WHERE id = :id")
    Mono<Player> updatePlayerName(String id, String newName);

    Flux<Player> findAllByOrderByTotalScoreDesc();
}
