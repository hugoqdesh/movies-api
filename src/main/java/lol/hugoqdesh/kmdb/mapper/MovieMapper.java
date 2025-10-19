package lol.hugoqdesh.kmdb.mapper;

import lol.hugoqdesh.kmdb.dto.MovieRequestDTO;
import lol.hugoqdesh.kmdb.dto.MovieResponseDTO;
import lol.hugoqdesh.kmdb.entities.Actor;
import lol.hugoqdesh.kmdb.entities.Genre;
import lol.hugoqdesh.kmdb.entities.Movie;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    public MovieResponseDTO toDTO(Movie movie) {
        MovieResponseDTO dto = new MovieResponseDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setDuration(movie.getDuration());

        dto.setGenres(movie.getGenres().stream().map(Genre::getName).collect(Collectors.toSet()));

        dto.setActors(movie.getActors().stream().map(Actor::getName).collect(Collectors.toSet()));

        return dto;
    }

    public Movie toEntity(MovieRequestDTO dto, Set<Genre> genres, Set<Actor> actors) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setDuration(dto.getDuration());
        movie.setGenres(genres);
        movie.setActors(actors);
        return movie;
    }
}
