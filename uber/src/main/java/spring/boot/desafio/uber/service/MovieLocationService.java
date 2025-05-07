package spring.boot.desafio.uber.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import spring.boot.desafio.uber.model.MovieLocation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovieLocationService {

    private final WebClient webClient;

    public MovieLocationService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://data.sfgov.org/resource/yitu-d5am.json")
                .build();
    }

    public List<MovieLocation> getAllMovies() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(MovieLocation.class)
                .collect(Collectors.toList())
                .block();
    }

    public List<MovieLocation> filterByTitle(String title) {
        return getAllMovies().stream()
                .filter(movieLocation -> movieLocation.getTitle() != null && movieLocation.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> autocomplete(String prefix) {
        return getAllMovies().stream()
                .map(MovieLocation::getTitle)
                .filter(Objects::nonNull)
                .filter(t -> t.toLowerCase().startsWith(prefix.toLowerCase()))
                .distinct()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
    }
}
