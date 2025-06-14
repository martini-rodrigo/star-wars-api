package br.com.starwars.core.movie.usecase.impl;

import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.port.MovieRepositoryPort;
import br.com.starwars.core.movie.usecase.GetMovieByTitleUseCase;
import br.com.starwars.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetMovieByTitleUseCaseImpl implements GetMovieByTitleUseCase {

    private final MovieRepositoryPort movieRepository;

    @Override
    public Movie getDetails(String title) {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie {0} not found.", title));
    }

}