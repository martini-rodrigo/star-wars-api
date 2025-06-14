package br.com.starwars.core.movie.usecase;

import br.com.starwars.core.exception.NotFoundException;
import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.port.MovieRepositoryPort;
import br.com.starwars.core.movie.usecase.impl.GetMovieByTitleCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetMovieByTitleUseCaseTest {

    @InjectMocks
    private GetMovieByTitleCaseImpl getMovieByTitleCase;

    @Mock
    private MovieRepositoryPort movieRepository;


    @Test
    void getDetails_shouldReturnMovie_whenMovieExists() {
        // Arrange
        Movie movie = Movie.builder().title("A New Hope").description("Description").build();
        when(movieRepository.findByTitle("A New Hope")).thenReturn(Optional.of(movie));

        // Act
        Movie result = getMovieByTitleCase.getDetails("A New Hope");

        // Assert
        assertNotNull(result);
        assertEquals("A New Hope", result.getTitle());
    }

    @Test
    void getDetails_shouldThrowNotFoundException_whenMovieNotFound() {
        // Arrange
        when(movieRepository.findByTitle("A New Hope")).thenReturn(Optional.empty());

        // Act
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            getMovieByTitleCase.getDetails("A New Hope");
        });
        assertEquals("Movie A New Hope not found.", exception.getMessage());
    }
}