package br.com.starwars.adapters.external.dto;

import br.com.starwars.core.movie.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SWAPIMovieResponseDTO {

    private List<Movie> results;
}
