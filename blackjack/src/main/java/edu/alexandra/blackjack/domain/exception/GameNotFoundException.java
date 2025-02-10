package edu.alexandra.blackjack.domain.exception;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String gameId) {
        super("Game with ID " + gameId + " not found.");
    }
}
