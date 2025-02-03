package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.response.RankingResponse;
import edu.alexandra.blackjack.domain.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;




@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RankingServiceImpl implements RankingService {

    private final PlayerRepository playerRepository;

    @Override
    public Flux<RankingResponse> getAll() {
        return playerRepository.findAllByOrderByTotalScoreDesc()
                .index()
                .map(tuple -> new RankingResponse(
                        (int) (tuple.getT1() + 1),
                        tuple.getT2().getId(),
                        tuple.getT2().getName(),
                        tuple.getT2().getTotalScore()
                ));
    }
}
