package br.com.starwars;

import br.com.starwars.core.service.MovieSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
class StarWarsApplication implements CommandLineRunner {

    private final MovieSyncService movieSyncService;

    public static void main(String[] args) {
        SpringApplication.run(StarWarsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        movieSyncService.loadAll();
    }
}
