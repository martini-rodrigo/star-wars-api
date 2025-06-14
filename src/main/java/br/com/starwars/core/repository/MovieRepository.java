package br.com.starwars.core.repository;

import br.com.starwars.core.domain.entity.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {

    private final List<Movie> movies = new ArrayList<>();

    public List<Movie> findAll() {
        return new ArrayList<>(movies);
    }

    public Optional<Movie> findByTitle(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    public void save(Movie movie) {
        if (!movies.contains(movie)) {
            movies.add(movie);
        }
    }

}