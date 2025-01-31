package edu.alexandra.blackjack.application.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ChangePlayerNameRequest {

    private final String playerNewName;
}
