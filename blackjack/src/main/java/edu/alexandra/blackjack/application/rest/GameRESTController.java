package edu.alexandra.blackjack.application.rest;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.application.rest.request.PlayGameRequest;
import edu.alexandra.blackjack.application.rest.response.GameResponse;
import edu.alexandra.blackjack.domain.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    public Mono<ResponseEntity<GameResponse>> createGame(@RequestBody @Valid CreateGameRequest newGame) {

        log.info("Creating game with player {}", newGame.getPlayerName());

        return gameService.createGame(newGame)
                .map(createGame -> ResponseEntity.status(HttpStatus.CREATED).body(createGame));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve an existing game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<GameResponse>> getGame(@PathVariable UUID id) {

        log.info("Getting game with id {}", id);

        return gameService.getGame(id)
                .map(game -> ResponseEntity.ok().body(game));
    }

    @PostMapping("/{id}/play")
    @Operation(summary = "Play a move",
            description = "Performs a move in an active game. Allowed moves: HIT or STAND.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Move executed",
                    content = @Content(schema = @Schema(implementation = GameResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid move"),
            @ApiResponse(responseCode = "500", description = "Internal error")
    })
    public Mono<ResponseEntity<GameResponse>> playMove(
            @RequestBody @Valid PlayGameRequest move,
            @PathVariable UUID id) {

        log.info("Playing {} in game {}", move.getMoveType(), id);

        return gameService.playGame(id, move)
                .map(game -> ResponseEntity.ok().body(game));
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
                        ? Mono.just(ResponseEntity.noContent().build())
                        : Mono.just(ResponseEntity.notFound().build())
                );
    }
}
