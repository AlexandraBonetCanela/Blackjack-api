package edu.alexandra.blackjack.application.rest.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public final class CreateGameRequest {

    @NotNull(message = "Player name is required")
    private final String playerName;

    @NotNull(message = "Bet quantity is required")
    private final BigDecimal moneyBet;
}
