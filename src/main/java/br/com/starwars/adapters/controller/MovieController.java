package br.com.starwars.adapters.controller;

import br.com.starwars.core.movie.domain.Movie;
import br.com.starwars.core.movie.dto.MovieUpdateDTO;
import br.com.starwars.core.movie.usecase.GetMovieByTitleUseCase;
import br.com.starwars.core.movie.usecase.ListMoviesUseCase;
import br.com.starwars.core.movie.usecase.UpdateMovieUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final ListMoviesUseCase listMoviesUseCase;
    private final GetMovieByTitleUseCase getMovieByTitleUseCase;
    private final UpdateMovieUseCase updateMovieUseCase;

    @GetMapping
    @Operation(summary = "Retrieve all movies", description = "Returns a list of all movies.")
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> movies = listMoviesUseCase.getAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{title}")
    @Operation(summary = "Get movie details", description = "Retrieves detailed information about a specific movie by its title.")
    public ResponseEntity<Movie> getDetails(@PathVariable String title) {
        Movie movie = getMovieByTitleUseCase.getDetails(title);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{title}/description")
    @Operation(summary = "Update movie description", description = "Updates the description of a specific movie by its title.")
    public ResponseEntity<Void> updateDescription(
            @PathVariable String title,
            @RequestBody @Valid MovieUpdateDTO movieUpdateDTO) {
        movieUpdateDTO.setTitle(title);
        updateMovieUseCase.updateDescription(movieUpdateDTO);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}