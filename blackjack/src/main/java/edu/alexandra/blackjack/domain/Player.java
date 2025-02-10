package edu.alexandra.blackjack.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Player {

    private String id;
    private String name;
    private BigDecimal totalScore;
}