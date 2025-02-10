package edu.alexandra.blackjack.application.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ChangePlayerNameRequest {

    @NotBlank(message = "Player name is cannot be empty")
    @Size(min = 3, max = 20, message = "Player name must be between 3 and 20 characters")
    private final String playerNewName;
}
