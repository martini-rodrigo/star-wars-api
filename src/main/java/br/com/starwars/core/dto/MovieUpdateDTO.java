package br.com.starwars.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieUpdateDTO {

    @JsonIgnore
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

}