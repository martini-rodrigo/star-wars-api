package br.com.starwars.core.dto;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import lombok.Getter;

@Getter
public class ResponseErrorDTO {

	private LocalDateTime timestamp = LocalDateTime.now(ZoneOffset.UTC);
	private List<String> errors;

	public ResponseErrorDTO(List<String> errors) {
		this.errors = errors;
	}

}
