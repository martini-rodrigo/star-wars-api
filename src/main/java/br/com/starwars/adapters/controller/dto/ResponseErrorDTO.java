package br.com.starwars.adapters.controller.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Getter
public class ResponseErrorDTO {

    private LocalDateTime timestamp = LocalDateTime.now(ZoneOffset.UTC);
    private List<String> errors;

    public ResponseErrorDTO(List<String> errors) {
        this.errors = errors;
    }

}
