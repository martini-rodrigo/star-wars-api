package br.com.starwars.core.movie.usecase;

import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.port.MovieRepositoryPort;
import br.com.starwars.core.movie.usecase.impl.ListMoviesUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListMoviesUseCaseTest {

    @InjectMocks
    private ListMoviesUseCaseImpl listMoviesUseCase;

    @Mock
    private MovieRepositoryPort movieRepository;


    @Test
    void getAll_shouldReturnListOfMovies() {
        // Arrange
        Movie movie1 = Movie.builder().title("A New Hope").description("Description").build();
        Movie movie2 = Movie.builder().title("The Empire Strikes Back").description("Description 2").build();;
        List<Movie> movies = Arrays.asList(movie1, movie2);

        when(movieRepository.findAll()).thenReturn(movies);

        // Act
        List<Movie> result = listMoviesUseCase.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("A New Hope", result.get(0).getTitle());
        assertEquals("The Empire Strikes Back", result.get(1).getTitle());
    }
}