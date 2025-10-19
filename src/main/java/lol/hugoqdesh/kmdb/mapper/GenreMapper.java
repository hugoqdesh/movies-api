package lol.hugoqdesh.kmdb.mapper;

import lol.hugoqdesh.kmdb.dto.GenreRequestDTO;
import lol.hugoqdesh.kmdb.dto.GenreResponseDTO;
import lol.hugoqdesh.kmdb.entities.Genre;
import lol.hugoqdesh.kmdb.entities.Movie;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenreMapper {

    public GenreResponseDTO toDTO(Genre genre) {
        GenreResponseDTO dto = new GenreResponseDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());

        dto.setMovies(genre.getMovies().stream().map(Movie::getTitle).collect(Collectors.toSet()));

        return dto;
    }

    public Genre toEntity(GenreRequestDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        return genre;
    }
}
