package br.com.starwars.core.usecase.impl;

import br.com.starwars.core.domain.entity.Movie;
import br.com.starwars.core.domain.exception.NotFoundException;
import br.com.starwars.core.repository.MovieRepository;
import br.com.starwars.core.usecase.IMovieUseCase;
import br.com.starwars.entrypoint.dto.MovieUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieUseCaseImpl implements IMovieUseCase {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getDetails(String title) {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie {0} not found.", title));
    }

    @Override
    public void updateDescription(MovieUpdateDTO movieUpdateDTO) {
        movieRepository.update(movieUpdateDTO);
    }
}