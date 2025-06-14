package br.com.starwars.core.movie.service;

import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.port.MovieExternalPort;
import br.com.starwars.core.movie.port.MovieRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieSyncService {

    private final MovieRepositoryPort movieRepository;
    private final MovieExternalPort movieExternal;

    public void loadAll() {
        List<Movie> movies = movieExternal.allMovies();
        movies.forEach(movieRepository::save);
    }
}