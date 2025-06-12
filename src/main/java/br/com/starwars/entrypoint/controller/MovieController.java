package br.com.starwars.entrypoint.controller;

import br.com.starwars.core.domain.entity.Movie;
import br.com.starwars.entrypoint.dto.MovieUpdateDTO;
import br.com.starwars.core.usecase.MovieUseCase;
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

    private final MovieUseCase movieUseCase;

    @GetMapping
    public ResponseEntity<List<Movie>> getAll() {
        List<Movie> movies = movieUseCase.getAll();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Movie> getDetails(@PathVariable String title) {
        Movie movie = movieUseCase.getDetails(title);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/{title}/description")
    public ResponseEntity<Void> updateDescription(
            @PathVariable String title,
            @RequestBody @Valid MovieUpdateDTO movieUpdateDTO) {
        movieUpdateDTO.setTitle(title);
        movieUseCase.updateDescription(movieUpdateDTO);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}