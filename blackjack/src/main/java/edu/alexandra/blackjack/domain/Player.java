package edu.alexandra.blackjack.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Player {

    @Id
    private String id;
    private String name;
    private BigDecimal totalScore;
}