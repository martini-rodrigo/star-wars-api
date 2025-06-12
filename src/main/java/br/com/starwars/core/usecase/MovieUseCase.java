package br.com.starwars.core.usecase;

import br.com.starwars.core.domain.entity.Movie;
import br.com.starwars.entrypoint.dto.MovieUpdateDTO;
import br.com.starwars.infrastructure.external.dto.SWAPIMovieResponseDTO;
import br.com.starwars.core.domain.exception.NotFoundException;
import br.com.starwars.infrastructure.external.client.SWAPIClient;
import br.com.starwars.core.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieUseCase {

    private final MovieRepository movieRepository;
    private final SWAPIClient swapiClient;

    @PostConstruct
    public void loadAll() {
        SWAPIMovieResponseDTO response = swapiClient.allMovies();
        List<Movie> movies = response.getResults();
        movies.forEach(movieRepository::save);
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie getDetails(String title) {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie {0} not found.", title));
    }

    public void updateDescription(MovieUpdateDTO movieUpdateDTO) {
        movieRepository.update(movieUpdateDTO);
    }
}