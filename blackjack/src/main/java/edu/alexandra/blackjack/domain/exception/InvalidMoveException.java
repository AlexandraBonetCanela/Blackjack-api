package edu.alexandra.blackjack.domain.exception;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException(String moveType) {
        super("Invalid move: " + moveType + ". Allowed moves are HIT or STAND.");
    }
}
