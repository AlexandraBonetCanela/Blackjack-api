package edu.alexandra.blackjack.domain.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleGameNotFound(GameNotFoundException ex) {
        log.error("Game could not be found {}", ex.getMessage());
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidMoveException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleInvalidMove(InvalidMoveException ex) {
        log.error("Move not valid. {}", ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(GameAlreadyFinishedException.class)
    public Mono<ResponseEntity<Map<String, String>>> handleGameAlreadyFinished(GameAlreadyFinishedException ex) {
        log.error("Cannot play this game because it is already finished. {}", ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public Mono<ResponseEntity<Map<String, String>>> handlePlayerNotFound(PlayerNotFoundException ex) {
        log.error("Player not found {}", ex.getMessage());
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private Mono<ResponseEntity<Map<String, String>>> createErrorResponse(HttpStatus status, String message) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", status.toString());
        errorResponse.put("error", message);
        return Mono.just(ResponseEntity.status(status).body(errorResponse));
    }
}
