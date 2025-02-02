package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.application.rest.request.PlayGameRequest;
import edu.alexandra.blackjack.application.rest.response.GameResponse;
import edu.alexandra.blackjack.domain.Card;
import edu.alexandra.blackjack.domain.Game;
import edu.alexandra.blackjack.domain.GameStatus;
import edu.alexandra.blackjack.domain.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private final PlayerService playerService;

    @Override
    public Mono<GameResponse> createGame(CreateGameRequest newGame) {

        return playerService.getOrCreatePlayer(newGame.getPlayerName())
                .map(player -> Game.builder()
                            .id(UUID.randomUUID())
                            .player(player)
                            .moneyBet(newGame.getMoneyBet())
                            .status(GameStatus.STARTED)
                            .build()
                            .dealInitialCards())
                .flatMap(gameRepository::save)
                .map(this::toGameResponse);
    }

    @Override
    public Mono<GameResponse> getGame(UUID id) {
        return gameRepository.findById(id)
                .map(this::toGameResponse);
    }

    @Override
    public Mono<GameResponse> playGame(UUID id, PlayGameRequest move) {

        return gameRepository.findById(id)
                .flatMap(game -> game.executeGameLogic(move.getMoveType()))
                .flatMap(gameRepository::save)
                .map(this::toGameResponse)
                .switchIfEmpty(Mono.error(new RuntimeException("Game not found")));
    }

    @Override
    public Mono<Boolean> deleteGame(UUID id) {
        return gameRepository.deleteById(id);
    }

    private GameResponse toGameResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .player(game.getPlayer())
                .playerHand(game.getPlayerHand())
                .dealerHand(maskDealerCard(game.getDealerHand(), game.getStatus()))
                .moneyBet(game.getMoneyBet())
                .status(game.getStatus())
                .gameResult(game.getGameResult())
                .build();
    }

    private List<Card> maskDealerCard(List<Card> dealerHand, GameStatus gameStatus){

        if (gameStatus == GameStatus.FINISHED || dealerHand == null || dealerHand.isEmpty()) {
            return dealerHand;
        }

        List<Card> maskedHand = new ArrayList<>(dealerHand);
        int lastCardIndex = maskedHand.size() -1;

        maskedHand.set(lastCardIndex, new Card("SECRET", "SECRET"));

        return maskedHand;
    }
}
