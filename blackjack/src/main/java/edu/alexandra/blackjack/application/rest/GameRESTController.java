package edu.alexandra.blackjack.application.rest;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.domain.Game;
import edu.alexandra.blackjack.domain.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/game")
public class GameRESTController {

    private final GameService gameService;

    @PostMapping
    public Mono<ResponseEntity<Game>> createGame(@RequestBody @Valid CreateGameRequest newGame) {
        log.info("Creating game with player {}", newGame.getPlayerName());
        return gameService.createGame(newGame)
                .map(createGame -> ResponseEntity.status(HttpStatus.CREATED).body(createGame))
                .onErrorResume(e -> {
                    log.error("Error creating game {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

}
