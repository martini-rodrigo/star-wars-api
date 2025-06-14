package br.com.starwars.core.movie.usecase.impl;

import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.port.MovieRepositoryPort;
import br.com.starwars.core.movie.usecase.ListMoviesUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListMoviesUseCaseImpl implements ListMoviesUseCase {

    private final MovieRepositoryPort movieRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }


}