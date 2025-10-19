package lol.hugoqdesh.kmdb.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreRequestDTO {
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}
