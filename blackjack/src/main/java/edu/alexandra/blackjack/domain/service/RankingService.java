package edu.alexandra.blackjack.domain.service;

import edu.alexandra.blackjack.application.rest.response.RankingResponse;
import reactor.core.publisher.Flux;




public interface RankingService {

    Flux<RankingResponse> getAll();
}
