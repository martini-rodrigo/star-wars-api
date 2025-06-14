package br.com.starwars.core.movie.usecase;

import br.com.starwars.core.movie.dto.MovieUpdateDTO;

public interface UpdateMovieUseCase {

    void updateDescription(MovieUpdateDTO movieUpdateDTO);
}