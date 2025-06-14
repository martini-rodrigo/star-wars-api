package br.com.starwars.core.movie.port;

import br.com.starwars.core.movie.domain.Movie;

import java.util.List;

public interface MovieExternalPort {

    List<Movie> allMovies();
}
