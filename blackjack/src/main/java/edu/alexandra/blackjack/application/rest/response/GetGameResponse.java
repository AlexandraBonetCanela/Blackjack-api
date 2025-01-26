package edu.alexandra.blackjack.application.rest.response;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class GetGameResponse {

    private final Long id;
    private final String playerName;
}
