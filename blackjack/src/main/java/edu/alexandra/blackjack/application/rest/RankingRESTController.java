package edu.alexandra.blackjack.application.rest;

import edu.alexandra.blackjack.application.rest.response.RankingResponse;
import edu.alexandra.blackjack.domain.service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/ranking")
public class RankingRESTController {

    private final RankingService rankingService;

    @GetMapping
    @Operation(summary = "Get ranking of Players")
    public Flux<RankingResponse> getRanking(){

        log.info("Getting Game Ranking");

        return rankingService.getAll();
    }

}
