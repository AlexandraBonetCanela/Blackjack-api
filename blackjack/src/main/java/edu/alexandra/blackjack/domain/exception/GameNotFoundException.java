package edu.alexandra.blackjack.domain.exception;

import java.util.UUID;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(UUID gameId) {
        super("Game with ID " + gameId + " not found.");
    }
}
