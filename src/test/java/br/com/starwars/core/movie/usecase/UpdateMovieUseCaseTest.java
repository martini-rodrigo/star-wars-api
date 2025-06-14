package br.com.starwars.core.movie.usecase.impl;

import br.com.starwars.core.exception.NotFoundException;
import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.dto.MovieUpdateDTO;
import br.com.starwars.core.movie.port.MovieRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateMovieUseCaseTest {

    @InjectMocks
    private UpdateMovieUseCaseImpl updateMovieUseCase;

    @Mock
    private MovieRepositoryPort movieRepository;

    @Test
    void updateDescription_shouldUpdateMovie_whenDescriptionIsDifferent() {
        // Arrange
        String title = "A New Hope";
        Movie existingMovie = Movie.builder().title(title).description("Old Description").version(1).build();

        MovieUpdateDTO movieUpdateDTO = MovieUpdateDTO.builder().title(title).description("New Description").build();

        when(movieRepository.findByTitle(title)).thenReturn(Optional.of(existingMovie));

        // Act
        updateMovieUseCase.updateDescription(movieUpdateDTO);

        // Assert
        assertEquals("New Description", existingMovie.getDescription());
        assertEquals(existingMovie.getVersion(), 2);
        verify(movieRepository).save(existingMovie);
    }

    @Test
    void updateDescription_shouldThrowNotFoundException_whenMovieDoesNotExist() {
        // Arrange
        MovieUpdateDTO movieUpdateDTO = MovieUpdateDTO.builder().title("A New Hope").description("Description").build();
        when(movieRepository.findByTitle(movieUpdateDTO.getTitle())).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            updateMovieUseCase.updateDescription(movieUpdateDTO);
        });
        assertEquals("Movie A New Hope not found.", exception.getMessage());
    }

    @Test
    void updateDescription_shouldNotUpdate_whenDescriptionIsSame() {
        // Arrange
        String title = "A New Hope";
        Movie existingMovie = Movie.builder().title(title).description("New Description").build();
        MovieUpdateDTO movieUpdateDTO = MovieUpdateDTO.builder().title(title).description("New Description").build();

        when(movieRepository.findByTitle(title)).thenReturn(Optional.of(existingMovie));

        // Act
        updateMovieUseCase.updateDescription(movieUpdateDTO);

        // Assert
        assertEquals("New Description", existingMovie.getDescription());
        verify(movieRepository, never()).save(any());
    }
}