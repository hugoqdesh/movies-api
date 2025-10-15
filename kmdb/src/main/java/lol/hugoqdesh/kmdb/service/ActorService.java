package lol.hugoqdesh.kmdb.service;

import lol.hugoqdesh.kmdb.entities.Actor;
import lol.hugoqdesh.kmdb.entities.Movie;
import lol.hugoqdesh.kmdb.repositories.ActorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor createActor(String name, LocalDate birthDate) {
        Actor actor = new Actor();
        actor.setName(name);
        actor.setBirthDate(birthDate);
        return actorRepository.save(actor);
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Actor getActorById(Long id) {
        return actorRepository.findById(id).orElseThrow(() -> new RuntimeException("Actor with id " + id + " not found"));
    }

    public List<Actor> getActorsByName(String name) {
        return actorRepository.findByName(name);
    }

    public Set<Movie> getAllMoviesByActorId(Long actorId) {
        Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new RuntimeException("Actor with id " + actorId + " not found"));
        return actor.getMovies();
    }

    public Actor updateActor(Long id, String name, LocalDate birthDate, Set<Movie> movies) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new RuntimeException("Actor with id " + id + " not found"));
        actor.setName(name);
        actor.setBirthDate(birthDate);
        actor.setMovies(movies);
        return actorRepository.save(actor);
    }

    public void deleteActorById(Long id) {
        actorRepository.deleteById(id);
    }
}
