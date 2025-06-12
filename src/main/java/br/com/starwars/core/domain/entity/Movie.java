package br.com.starwars.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private String title;
    private String description;

    @JsonProperty("opening_crawl")
    private String openingCrawl;

    private String director;
    private String producer;

    @JsonProperty("release_date")
    private LocalDate releaseDate;
    private int version = 1; // start the version
}