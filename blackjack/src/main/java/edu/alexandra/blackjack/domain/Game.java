package edu.alexandra.blackjack.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Game {

    private final Long id;
    private final Player player;
    private final GameStatus status;
}
