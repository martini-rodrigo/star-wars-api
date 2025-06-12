package br.com.starwars.core.usecase;

import br.com.starwars.core.domain.entity.Movie;
import br.com.starwars.core.domain.exception.NotFoundException;
import br.com.starwars.core.repository.MovieRepository;
import br.com.starwars.core.usecase.impl.MovieUseCaseImpl;
import br.com.starwars.entrypoint.dto.MovieUpdateDTO;
import br.com.starwars.infrastructure.external.client.SWAPIClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieUseCaseTest {

    @InjectMocks
    private MovieUseCaseImpl movieUseCase;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private SWAPIClient swapiClient;


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