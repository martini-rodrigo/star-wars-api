package br.com.starwars.adapters.repository.adapter;


import br.com.starwars.adapters.repository.persistence.MovieRepository;
import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.port.MovieRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieRepositoryAdapter implements MovieRepositoryPort {

    private final MovieRepository movieRepository;

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

}