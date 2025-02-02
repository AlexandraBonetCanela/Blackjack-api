package edu.alexandra.blackjack.application.rest;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.application.rest.request.PlayGameRequest;
import edu.alexandra.blackjack.domain.Game;
import edu.alexandra.blackjack.domain.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/game")
public class GameRESTController {

    private final GameService gameService;

    @PostMapping
    @Operation(summary = "Create a new game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<Game>> createGame(@RequestBody @Valid CreateGameRequest newGame) {

        log.info("Creating game with player {}", newGame.getPlayerName());

        return gameService.createGame(newGame)
                .map(createGame -> ResponseEntity.status(HttpStatus.CREATED).body(createGame))
                .onErrorResume(e -> {
                    log.error("Error creating game {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve an existing game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<Game>> getGame(@PathVariable UUID id) {

        log.info("Getting game with id {}", id);

        return gameService.getGame(id)
                .map(game -> ResponseEntity.status(HttpStatus.OK).body(game))
                .onErrorResume(e -> {
                    log.error("Error while retrieving game {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @PostMapping("/{id}/play")
    public Mono<ResponseEntity<Game>> playMove(@RequestBody @Valid PlayGameRequest move) {

        log.info("Playing {} in game {}", move.getMoveType(), move.getGameId());

        return gameService.playGame(move)
                .map(game -> ResponseEntity.status(HttpStatus.OK).body(game))
                .onErrorResume(e -> {
                    log.error("Error while playing move", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a game by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Game successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<Void>> deleteGame(@PathVariable UUID id) {

        log.info("Deleting game with id {}", id);

        return gameService.deleteGame(id)
                .flatMap(deleted -> deleted
                        ? Mono.just(ResponseEntity.noContent().<Void>build()) // ✅ 204 No Content if deleted
                        : Mono.just(ResponseEntity.notFound().<Void>build()) // ✅ 404 Not Found if game doesn't exist
                )
                .onErrorResume(e -> {
                    log.error("Error deleting game {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()); // ✅ 500 for errors
                });
    }
}
