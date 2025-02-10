package edu.alexandra.blackjack.domain.exception;


public class GameAlreadyFinishedException extends RuntimeException {

    public GameAlreadyFinishedException(String id) {
        super("Game with ID: " + id + " already finished. Play another game");
    }
}
