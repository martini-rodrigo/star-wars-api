package br.com.starwars.core.repository;

import br.com.starwars.core.domain.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class MovieRepository {

    private final List<Movie> movies = new ArrayList<>();

    public List<Movie> findAll() {
        log.info("Finding all movies. Total movies: {}", movies.size());
        return new ArrayList<>(movies);
    }

    public Optional<Movie> findByTitle(String title) {
        log.info("Searching for movie with title: {}", title);
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    public void save(Movie movie) {
        if (!movies.contains(movie)) {
            log.info("Saving movie: {}", movie.getTitle());
            movies.add(movie);
        }
    }

}