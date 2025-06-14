package br.com.starwars.core.usecase.impl;

import br.com.starwars.core.domain.entity.Movie;
import br.com.starwars.core.domain.exception.NotFoundException;
import br.com.starwars.core.repository.MovieRepository;
import br.com.starwars.core.usecase.IMovieUseCase;
import br.com.starwars.entrypoint.dto.MovieUpdateDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
        Movie movie = getDetails(movieUpdateDTO.getTitle());

        if (!movieUpdateDTO.getDescription().equals(movie.getDescription())) {
            movie.setDescription(movieUpdateDTO.getDescription());
            movie.setVersion(movie.getVersion() + 1);

            movieRepository.save(movie);
            log.info("Movie {} updated successfully.", movie.getTitle());
        } else {
            log.info("No changes made to the description for movie {}", movie.getTitle());
        }
    }
}