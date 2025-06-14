package br.com.starwars.adapters.repository.adapter;

import br.com.starwars.adapters.external.client.SWAPIClient;
import br.com.starwars.adapters.external.dto.SWAPIMovieResponseDTO;
import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.port.MovieExternalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieExternalAdapter implements MovieExternalPort {

    private final SWAPIClient swapiClient;

    @Override
    public List<Movie> allMovies() {
        SWAPIMovieResponseDTO response = swapiClient.allMovies();
        return response.getResults();
    }
}