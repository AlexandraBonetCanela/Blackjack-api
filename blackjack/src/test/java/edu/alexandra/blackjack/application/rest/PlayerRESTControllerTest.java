package edu.alexandra.blackjack.application.rest;

import edu.alexandra.blackjack.application.rest.request.ChangePlayerNameRequest;
import edu.alexandra.blackjack.domain.Player;
import edu.alexandra.blackjack.domain.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(PlayerRESTController.class)
@MockBean(PlayerService.class)
public class PlayerRESTControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerRESTController playerRESTController;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(playerRESTController).build();
    }

    @Test
    void changePlayerName_Success_Test(){

        UUID id = UUID.randomUUID();
        ChangePlayerNameRequest request = new ChangePlayerNameRequest("Pia");
        Player updatedPlayer = new Player(id, request.getPlayerNewName(), new BigDecimal(100));

        when(playerService.changePlayerName(any(UUID.class), any(ChangePlayerNameRequest.class)))
                .thenReturn(Mono.just(updatedPlayer));

        webTestClient.put()
                .uri("/player/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Player.class)
                .isEqualTo(updatedPlayer);
    }

    @Test
    void changePlayerName_NotFound_Test(){

        UUID id = UUID.randomUUID();
        ChangePlayerNameRequest request = new ChangePlayerNameRequest("Pia");

        when(playerService.changePlayerName(any(UUID.class), any(ChangePlayerNameRequest.class)))
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

        String invalidRequestBody = "{ \"name\": \"\" }";
        UUID playerId = UUID.randomUUID();

        webTestClient.put()
                .uri("/player/{id}", playerId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidRequestBody)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
