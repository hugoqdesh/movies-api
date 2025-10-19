package lol.hugoqdesh.kmdb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MovieResponseDTO {
    private Long id;
    private String title;
    private Integer releaseYear;
    private Integer duration;
    private Set<String> genres;
    private Set<String> actors;
}
