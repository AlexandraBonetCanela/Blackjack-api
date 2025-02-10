package edu.alexandra.blackjack.domain;

import edu.alexandra.blackjack.application.rest.request.ChangePlayerNameRequest;
import edu.alexandra.blackjack.domain.repository.PlayerRepository;
import edu.alexandra.blackjack.domain.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceUnitTest {

     @Mock
     private PlayerRepository playerRepository;

     @InjectMocks
     private PlayerService playerService;

     private String playerId;
     private Player player;

     @BeforeEach
     void setUp() {
         playerId = UUID.randomUUID().toString();
         player = new Player();
         player.setId(playerId);
         player.setName("John Doe");
         player.setTotalScore(BigDecimal.valueOf(1000));
     }


     @Test
     void changePlayerName_Success_Test() {

         ChangePlayerNameRequest request = new ChangePlayerNameRequest("Pia");
         Player updatedPlayer = new Player(playerId, request.getPlayerNewName(), new BigDecimal(100));

         when(playerRepository.findById(playerId)).thenReturn(Mono.just(player));
         when(playerRepository.changePlayerName(playerId, request.getPlayerNewName()))
                 .thenReturn(Mono.just(updatedPlayer));

     }
}
