package br.com.starwars.core.movie.usecase.impl;

import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.dto.MovieUpdateDTO;
import br.com.starwars.core.movie.port.MovieRepositoryPort;
import br.com.starwars.core.movie.usecase.UpdateMovieUseCase;
import br.com.starwars.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateMovieUseCaseImpl implements UpdateMovieUseCase {

    private final MovieRepositoryPort movieRepository;

    @Override
    public void updateDescription(MovieUpdateDTO movieUpdateDTO) {
        Movie movie = movieRepository.findByTitle(movieUpdateDTO.getTitle())
                .orElseThrow(() -> new NotFoundException("Movie {0} not found.", movieUpdateDTO.getTitle()));

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