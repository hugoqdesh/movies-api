package lol.hugoqdesh.kmdb.service;

import lol.hugoqdesh.kmdb.dto.ActorRequestDTO;
import lol.hugoqdesh.kmdb.dto.ActorResponseDTO;
import lol.hugoqdesh.kmdb.entities.Actor;
import lol.hugoqdesh.kmdb.entities.Movie;
import lol.hugoqdesh.kmdb.exception.ResourceNotFoundException;
import lol.hugoqdesh.kmdb.mapper.ActorMapper;
import lol.hugoqdesh.kmdb.repositories.ActorRepository;
import lol.hugoqdesh.kmdb.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final ActorMapper actorMapper;

    public ActorService(ActorRepository actorRepository, MovieRepository movieRepository, ActorMapper actorMapper) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
        this.actorMapper = actorMapper;
    }

    public ActorResponseDTO createActor(ActorRequestDTO dto) {
        Set<Movie> movies = new HashSet<>(movieRepository.findAllById(dto.getMovieIds()));
        Actor actor = actorMapper.toEntity(dto, movies);
        return actorMapper.toDTO(actorRepository.save(actor));
    }

    @Transactional
    public List<ActorResponseDTO> getAllActors() {
        return actorRepository.findAll().stream().map(actorMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public ActorResponseDTO getActorById(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor with id " + id + " not found"));
        return actorMapper.toDTO(actor);
    }

    @Transactional
    public List<ActorResponseDTO> getActorsByName(String name) {
        return actorRepository.findByName(name).stream().map(actorMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public ActorResponseDTO updateActor(Long id, ActorRequestDTO dto) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor with id " + id + " not found"));

        Set<Movie> movies = new HashSet<>(movieRepository.findAllById(dto.getMovieIds()));

        actor.setName(dto.getName());
        actor.setBirthDate(dto.getBirthDate());
        actor.setMovies(movies);

        return actorMapper.toDTO(actorRepository.save(actor));
    }

    @Transactional
    public void deleteActorById(Long id, boolean force) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Actor with id " + id + " not found"));

        if (!force && !actor.getMovies().isEmpty()) {
            throw new IllegalStateException("Unable to delete actor '" + actor.getName() + "' as they are associated with " + actor.getMovies().size() + " movies.");
        }

        if (force) {
            actor.getMovies().forEach(movie -> movie.getActors().remove(actor));
        }

        actorRepository.delete(actor);
    }

}
