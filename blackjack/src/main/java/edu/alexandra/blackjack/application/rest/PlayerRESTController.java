package edu.alexandra.blackjack.application.rest;

import edu.alexandra.blackjack.application.rest.request.ChangePlayerNameRequest;
import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.domain.service.PlayerService;
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
@RequestMapping("/player")
public class PlayerRESTController {

    private final PlayerService playerService;

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Player>> changeName(
            @RequestBody @Valid ChangePlayerNameRequest newName,
            @PathVariable @Valid UUID id) {

        log.info("Changing Player name {} with id {}", newName, id);

        return playerService.changePlayerName(id, newName)
                .map(changedPlayer -> ResponseEntity.status(HttpStatus.OK).body(changedPlayer))
                .onErrorResume(e -> {
                    log.error("Error updating Player name {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

}
