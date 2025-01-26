package edu.alexandra.blackjack.application.rest;

import edu.alexandra.blackjack.application.rest.request.CreateGameRequest;
import edu.alexandra.blackjack.application.rest.response.GetGameResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/game")
public class GameRESTController {

    @PostMapping
    public Mono<ResponseEntity<GetGameResponse>> createGame(@RequestBody @Valid CreateGameRequest newGame) {

    }

}
