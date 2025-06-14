package br.com.starwars.core.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdateDTO {

    @JsonIgnore
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

}