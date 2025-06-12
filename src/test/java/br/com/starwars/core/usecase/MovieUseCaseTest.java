package br.com.starwars.core.usecase;

import br.com.starwars.core.domain.entity.Movie;
import br.com.starwars.entrypoint.dto.MovieUpdateDTO;
import br.com.starwars.infrastructure.external.dto.SWAPIMovieResponseDTO;
import br.com.starwars.core.domain.exception.NotFoundException;
import br.com.starwars.infrastructure.external.client.SWAPIClient;
import br.com.starwars.core.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieUseCaseTest {

    @InjectMocks
    private MovieUseCase movieUseCase;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private SWAPIClient swapiClient;

    @Test
    void testLoadAll() {
        List<Movie> mockMovies = new ArrayList<>();
        mockMovies.add(Movie.builder().title("Star Wars 1").description("Description 1").build());
        mockMovies.add(Movie.builder().title("Star Wars 2").description("Description 2").build());

        SWAPIMovieResponseDTO mockResponse = new SWAPIMovieResponseDTO();
        mockResponse.setResults(mockMovies);
        when(swapiClient.allMovies()).thenReturn(mockResponse);

        movieUseCase.loadAll();

        for (Movie movie : mockMovies) {
            verify(movieRepository).save(movie);
        }
    }

    @Test
    void testGetAll() {
        List<Movie> mockMovies = List.of(Movie.builder().title("Star Wars 1").description("Description 1").build());
        when(movieRepository.findAll()).thenReturn(mockMovies);

        List<Movie> movies = movieUseCase.getAll();

        assertEquals(mockMovies, movies);
    }

    @Test
    void testGetDetails_MovieExists() {
        String title = "Star Wars 1";
        Movie mockMovie = Movie.builder().title(title).description("Description 1").build();
        when(movieRepository.findByTitle(title)).thenReturn(Optional.of(mockMovie));

        Movie movie = movieUseCase.getDetails(title);

        assertEquals(mockMovie, movie);
    }

    @Test
    void testGetDetails_MovieNotFound() {
        String title = "Non Existing Movie";
        when(movieRepository.findByTitle(title)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> movieUseCase.getDetails(title));
        assertEquals("Movie Non Existing Movie not found.", exception.getMessage());
    }

    @Test
    void testUpdateDescription() {
        MovieUpdateDTO updateDTO = new MovieUpdateDTO("Star Wars", "Description");

        movieUseCase.updateDescription(updateDTO);
        verify(movieRepository).update(updateDTO);
    }
}