package br.com.starwars.core.service;

import br.com.starwars.core.domain.entity.Movie;
import br.com.starwars.core.repository.MovieRepository;
import br.com.starwars.infrastructure.external.client.SWAPIClient;
import br.com.starwars.infrastructure.external.dto.SWAPIMovieResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieSyncService {

    private final MovieRepository movieRepository;
    private final SWAPIClient swapiClient;

    public void loadAll() {
        SWAPIMovieResponseDTO response = swapiClient.allMovies();
        List<Movie> movies = response.getResults();
        movies.forEach(movieRepository::save);
    }
}