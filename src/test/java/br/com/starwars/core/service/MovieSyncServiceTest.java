package br.com.starwars.core.service;

import br.com.starwars.core.domain.entity.Movie;
import br.com.starwars.core.repository.MovieRepository;
import br.com.starwars.infrastructure.external.client.SWAPIClient;
import br.com.starwars.infrastructure.external.dto.SWAPIMovieResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieSyncServiceTest {

    @InjectMocks
    private MovieSyncService movieSyncService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private SWAPIClient swapiClient;

    @Test
    void testLoadAll() {
        List<Movie> mockMovies = List.of(
                Movie.builder().title("Star Wars 1").description("Desc 1").build(),
                Movie.builder().title("Star Wars 2").description("Desc 2").build()
        );

        SWAPIMovieResponseDTO response = new SWAPIMovieResponseDTO();
        response.setResults(mockMovies);

        when(swapiClient.allMovies()).thenReturn(response);

        movieSyncService.loadAll();

        for (Movie movie : mockMovies) {
            verify(movieRepository).save(movie);
        }
    }

}
