package lol.hugoqdesh.kmdb.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ActorRequestDTO {
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    @Past
    private LocalDate birthDate;

    private Set<Long> movieIds;
}
