package edu.alexandra.blackjack.application.rest.request;

import edu.alexandra.blackjack.domain.MoveType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public final class PlayGameRequest {

    @NotNull(message = "Game ID is required")
    private final UUID gameId;

    @NotNull(message = "Move Type is required")
    private final MoveType moveType;
}
