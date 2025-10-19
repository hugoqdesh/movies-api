package lol.hugoqdesh.kmdb.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MovieRequestDTO {
    @NotBlank
    @Size(min = 1, max = 200)
    private String title;

    @NotNull
    @Min(1888)
    @Max(2100)
    private Integer releaseYear;

    @NotNull
    @Min(1)
    @Max(600)
    private Integer duration;

    private Set<Long> genreIds;

    private Set<Long> actorIds;
}
