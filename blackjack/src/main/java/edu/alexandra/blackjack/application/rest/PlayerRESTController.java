package edu.alexandra.blackjack.application.rest;

import edu.alexandra.blackjack.application.rest.request.ChangePlayerNameRequest;
import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.domain.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/player")
public class PlayerRESTController {

    private final PlayerService playerService;

    @PutMapping("/{id}")
    @Operation(summary = "Change a player's name by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player name successfully updated"),
            @ApiResponse(responseCode = "400", description = "Invalid request format"),
            @ApiResponse(responseCode = "404", description = "Player not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<Player>> changeName(
            @RequestBody @Valid ChangePlayerNameRequest newName,
            @PathVariable @Valid String id) {

        log.info("Changing Player name {} with id {}", newName.getPlayerNewName(), id);

        return playerService.changePlayerName(id, newName)
                .map(changedPlayer -> ResponseEntity.ok().body(changedPlayer))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
