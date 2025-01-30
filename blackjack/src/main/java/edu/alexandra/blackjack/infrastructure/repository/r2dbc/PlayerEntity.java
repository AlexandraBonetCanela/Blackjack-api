package edu.alexandra.blackjack.infrastructure.repository.r2dbc;


import edu.alexandra.blackjack.domain.Player;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table("player")
public class PlayerEntity {

    @Id
    private String id;
    private String name;
    private BigDecimal totalScore;



    public static Player toDomain(PlayerEntity playerEntity) {
        return Player.builder()
                .id(playerEntity.getId() != null? UUID.fromString(playerEntity.getId()) : null)
                .name(playerEntity.getName())
                .totalScore(playerEntity.getTotalScore())
                .build();
    }

    public static PlayerEntity toEntity(Player player){
        return PlayerEntity.builder()
                .id(player.getId() != null? player.getId().toString() : null)
                .name(player.getName())
                .totalScore(player.getTotalScore())
                .build();
    }
}
