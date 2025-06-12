package br.com.starwars.infrastructure.external.client;

import br.com.starwars.infrastructure.external.dto.SWAPIMovieResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "starwars-client", url = "${feign-client.starwars.url}")
public interface SWAPIClient {

    @GetMapping("/films/")
    SWAPIMovieResponseDTO allMovies();
}
