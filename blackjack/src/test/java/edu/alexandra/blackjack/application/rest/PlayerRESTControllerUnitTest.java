package edu.alexandra.blackjack.application.rest;

import edu.alexandra.blackjack.application.rest.request.ChangePlayerNameRequest;
import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.domain.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(PlayerRESTController.class)
@MockBean(PlayerService.class)
public class PlayerRESTControllerUnitTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PlayerService playerService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void changePlayerName_Success_Test(){

        // Creating test data
        String id = UUID.randomUUID().toString();
        ChangePlayerNameRequest request = new ChangePlayerNameRequest("Pia");
        Player updatedPlayer = new Player(id, request.getPlayerNewName(), new BigDecimal(100));

        // Mocking the service
        when(playerService.changePlayerName(any(UUID.class).toString(), any(ChangePlayerNameRequest.class)))
                .thenReturn(Mono.just(updatedPlayer));

        // Simulating an HTTP request
        webTestClient.put()
                .uri("/player/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()

                // Verifying response
                .expectStatus().isOk()
                .expectBody(Player.class)
                .value(response -> assertEquals(updatedPlayer.getName(), response.getName()));
    }

    @Test
    void changePlayerName_NotFound_Test(){

        UUID id = UUID.randomUUID();
        ChangePlayerNameRequest request = new ChangePlayerNameRequest("Pia");

        when(playerService.changePlayerName(any(UUID.class).toString(), any(ChangePlayerNameRequest.class)))
                .thenReturn(Mono.empty());

        webTestClient.put()
                .uri("/player/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isNotFound();

    }

    @Test
    void changePlayerName_BadRequest_Test(){

        String invalidRequestBody = "{ \"playerNewName\": \"\" }";
        UUID playerId = UUID.randomUUID();

        webTestClient.put()
                .uri("/player/{id}", playerId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidRequestBody)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .consumeWith(response -> System.out.println("Response: " + response.getResponseBody()));
    }
}
