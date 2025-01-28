package edu.alexandra.blackjack.application.rest.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;;

@Getter
@AllArgsConstructor
public final class CreateGameRequest {

    @NotNull(message = "Player name is required")
    private final String playerName;
}
