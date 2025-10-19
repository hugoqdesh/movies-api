package lol.hugoqdesh.kmdb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GenreResponseDTO {
    private Long id;
    private String name;
    private Set<String> movies;
}
