package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.application.rest.request.PlayGameRequest;
import edu.alexandra.blackjack.domain.Deck;
import edu.alexandra.blackjack.domain.Game;
import edu.alexandra.blackjack.domain.GameStatus;
import edu.alexandra.blackjack.domain.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private final PlayerService playerService;

    @Override
    public Mono<Game> createGame(CreateGameRequest newGame) {

        return playerService.getOrCreatePlayer(newGame.getPlayerName())
                .flatMap(player -> {
                    Game game = Game.builder()
                            .id(UUID.randomUUID())
                            .player(player)
                            .deck(new Deck())
                            .moneyBet(newGame.getMoneyBet())
                            .status(GameStatus.STARTED).build();
                    return gameRepository.createGame(game);
                });
    }

    @Override
    public Mono<Game> getGame(UUID id) {
        return gameRepository.findById(id);
    }
/*
    @Override
    public Mono<Game> playGame(PlayGameRequest move) {
        return null;
    }

 */
    @Override
    public Mono<Boolean> deleteGame(UUID id) {
        return gameRepository.deleteById(id);
    }
}
