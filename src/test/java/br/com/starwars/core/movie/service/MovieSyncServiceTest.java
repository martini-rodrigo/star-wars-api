package br.com.starwars.core.movie.service;

import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.port.MovieExternalPort;
import br.com.starwars.core.movie.port.MovieRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieSyncServiceTest {

    @InjectMocks
    private MovieSyncService movieSyncService;

    @Mock
    private MovieRepositoryPort movieRepository;

    @Mock
    private MovieExternalPort movieExternal;


    @Test
    void loadAll_shouldSaveAllMovies() {
        // Arrange
        Movie movie1 = Movie.builder().title("Movie 1").description("Description 1").build();
        Movie movie2 = Movie.builder().title("Movie 2").description("Description 2").build();
        List<Movie> movies = Arrays.asList(movie1, movie2);

        when(movieExternal.allMovies()).thenReturn(movies);

        // Act
        movieSyncService.loadAll();

        // Assert
        verify(movieRepository).save(movie1);
        verify(movieRepository).save(movie2);
    }
}