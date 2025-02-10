package edu.alexandra.blackjack.application.rest.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class RankingResponse {

    private final int position;
    private final String playerId;
    private final String name;
    private final BigDecimal totalScore;
}
