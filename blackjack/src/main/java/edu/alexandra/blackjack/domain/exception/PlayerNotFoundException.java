package edu.alexandra.blackjack.domain.exception;

import java.util.UUID;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(UUID id) {
        super("Player with ID: " + id + " not found");
    }
}
