package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.domain.Game;
import edu.alexandra.blackjack.domain.GameStatus;
import edu.alexandra.blackjack.domain.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
                            .player(player)
                            .status(GameStatus.STARTED).build();
                    return gameRepository.createGame(game);
                });
    }

    @Override
    public Mono<Game> getGame(Long id) {
        return gameRepository.findById(id);
    }
}
