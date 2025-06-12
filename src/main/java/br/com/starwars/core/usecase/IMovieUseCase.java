package br.com.starwars.core.usecase;

import br.com.starwars.core.domain.entity.Movie;
import br.com.starwars.entrypoint.dto.MovieUpdateDTO;

import java.util.List;

public interface IMovieUseCase {

    List<Movie> getAll();

    Movie getDetails(String title);

    void updateDescription(MovieUpdateDTO movieUpdateDTO);
}