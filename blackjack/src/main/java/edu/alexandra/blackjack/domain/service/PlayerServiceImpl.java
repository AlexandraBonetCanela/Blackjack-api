package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.domain.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PlayerServiceImpl implements PlayerService{
    @Override
    public Mono<Player> getPlayer(Long id) {
        return null;
    }
}
