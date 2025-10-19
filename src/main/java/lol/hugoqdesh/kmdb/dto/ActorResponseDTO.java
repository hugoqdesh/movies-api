package lol.hugoqdesh.kmdb.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ActorResponseDTO {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private Set<String> movies;
}
