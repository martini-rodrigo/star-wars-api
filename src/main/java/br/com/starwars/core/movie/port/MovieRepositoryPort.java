package br.com.starwars.core.movie.port;

import br.com.starwars.core.movie.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepositoryPort {

    List<Movie> findAll();

    Optional<Movie> findByTitle(String title);

    void save(Movie movie);
}