package br.com.starwars.core.dto;

import br.com.starwars.core.domain.Movie;
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
