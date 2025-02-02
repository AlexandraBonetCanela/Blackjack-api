package edu.alexandra.blackjack.application.rest.response;

import edu.alexandra.blackjack.domain.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class GameResponse {

    private final UUID id;
    private final Player player;
    private final List<Card> playerHand;
    private final List<Card> dealerHand;
    private final BigDecimal moneyBet;
    private final GameStatus status;
    private final GameResult gameResult;
}
