package br.com.starwars.core.movie.usecase;

import br.com.starwars.core.movie.domain.Movie;

import java.util.List;

public interface ListMoviesUseCase {

    List<Movie> getAll();
}