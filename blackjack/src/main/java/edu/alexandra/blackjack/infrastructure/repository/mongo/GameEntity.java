package edu.alexandra.blackjack.infrastructure.repository.mongo;

import edu.alexandra.blackjack.domain.GameStatus;
import edu.alexandra.blackjack.domain.Player;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Document(collection = "games")
public class GameEntity {

    @Id
    private String id;

    private Player player;

    private GameStatus status;
}