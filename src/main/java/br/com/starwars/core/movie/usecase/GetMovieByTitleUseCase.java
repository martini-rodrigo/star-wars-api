package br.com.starwars.core.movie.usecase;

import br.com.starwars.core.movie.domain.Movie;

public interface GetMovieByTitleUseCase {

    Movie getDetails(String title);
}