package lol.hugoqdesh.kmdb.mapper;

import lol.hugoqdesh.kmdb.dto.ActorRequestDTO;
import lol.hugoqdesh.kmdb.dto.ActorResponseDTO;
import lol.hugoqdesh.kmdb.entities.Actor;
import lol.hugoqdesh.kmdb.entities.Movie;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ActorMapper {

    public ActorResponseDTO toDTO(Actor actor) {
        ActorResponseDTO dto = new ActorResponseDTO();
        dto.setId(actor.getId());
        dto.setName(actor.getName());
        dto.setBirthDate(actor.getBirthDate());

        dto.setMovies(actor.getMovies().stream().map(Movie::getTitle) .collect(Collectors.toSet()));

        return dto;
    }

    public Actor toEntity(ActorRequestDTO dto, Set<Movie> movies) {
        Actor actor = new Actor();
        actor.setName(dto.getName());
        actor.setBirthDate(dto.getBirthDate());
        actor.setMovies(movies);
        return actor;
    }
}
