package br.com.starwars.infrastructure.repository;

import br.com.starwars.core.domain.Movie;
import br.com.starwars.core.dto.MovieUpdateDTO;
import br.com.starwars.core.exception.NotFoundException;
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
        movies.add(movie);
    }

    public void update(MovieUpdateDTO movieUpdateDTO) {
        Movie toUpdateMovie = findByTitle(movieUpdateDTO.getTitle())
                .orElseThrow(() -> new NotFoundException("Movie {0} not found.", movieUpdateDTO.getTitle()));
        if (!movieUpdateDTO.getDescription().equals(toUpdateMovie.getDescription())) {
            toUpdateMovie.setDescription(movieUpdateDTO.getDescription());
            toUpdateMovie.setVersion(toUpdateMovie.getVersion() + 1); // Increase the version
        }
    }

}