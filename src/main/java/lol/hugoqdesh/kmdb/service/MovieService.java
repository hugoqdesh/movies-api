package lol.hugoqdesh.kmdb.service;

import lol.hugoqdesh.kmdb.dto.ActorResponseDTO;
import lol.hugoqdesh.kmdb.dto.MovieRequestDTO;
import lol.hugoqdesh.kmdb.dto.MovieResponseDTO;
import lol.hugoqdesh.kmdb.entities.Actor;
import lol.hugoqdesh.kmdb.entities.Genre;
import lol.hugoqdesh.kmdb.entities.Movie;
import lol.hugoqdesh.kmdb.exception.ResourceNotFoundException;
import lol.hugoqdesh.kmdb.mapper.ActorMapper;
import lol.hugoqdesh.kmdb.mapper.MovieMapper;
import lol.hugoqdesh.kmdb.repositories.ActorRepository;
import lol.hugoqdesh.kmdb.repositories.GenreRepository;
import lol.hugoqdesh.kmdb.repositories.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;
    private final ActorMapper actorMapper;

    public MovieService(MovieRepository movieRepository,
                        ActorRepository actorRepository,
                        GenreRepository genreRepository,
                        MovieMapper movieMapper,
                        ActorMapper actorMapper) {

        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
        this.movieMapper = movieMapper;
        this.actorMapper = actorMapper;
    }

    public MovieResponseDTO createMovie(MovieRequestDTO dto) {
        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(dto.getGenreIds()));
        Set<Actor> actors = new HashSet<>(actorRepository.findAllById(dto.getActorIds()));

        Movie movie = movieMapper.toEntity(dto, genres, actors);
        return movieMapper.toDTO(movieRepository.save(movie));
    }

    @Transactional
    public Page<MovieResponseDTO> getAllMovies(int page, int size) {
        if (page < 0) {
            throw new ResourceNotFoundException("Page index must not be less than zero");
        }

        if (size <= 0 || size > 100) {
            throw new ResourceNotFoundException("Page size must be between 1 and 100");
        }

        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable).map(movieMapper::toDTO);
    }

    @Transactional
    public MovieResponseDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found"));

        return movieMapper.toDTO(movie);
    }

    @Transactional
    public List<MovieResponseDTO> getMovieByGenreId(Long genreId) {
        return movieRepository.findByGenresId(genreId)
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MovieResponseDTO> getMovieByReleaseYear(Integer releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear)
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MovieResponseDTO> getMovieByActorId(Long actorId) {
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResourceNotFoundException("Actor with id " + actorId + " not found"));

        return movieRepository.findByActors(actor)
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ActorResponseDTO> getActorByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with id " + movieId + " not found"));

        return movie.getActors()
                .stream()
                .map(actorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MovieResponseDTO updateMovie(Long id, MovieRequestDTO dto) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found"));

        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(dto.getGenreIds()));
        Set<Actor> actors = new HashSet<>(actorRepository.findAllById(dto.getActorIds()));

        movie.setTitle(dto.getTitle());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setDuration(dto.getDuration());
        movie.setGenres(genres);
        movie.setActors(actors);
        return movieMapper.toDTO(movieRepository.save(movie));
    }

    @Transactional
    public void deleteMovie(Long id, boolean force) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie with id " + id + " not found"));

        if(!force && !movie.getActors().isEmpty()) {
            throw new ResourceNotFoundException("Unable to delete movie '" + movie.getTitle() + "' as they are associated with " + movie.getActors().size() + " actors");
        }

        if (force) {
            movie.getActors().forEach(actor -> movie.getActors().remove(actor));
        }

        movieRepository.deleteById(id);
    }

    @Transactional
    public List<MovieResponseDTO> searchMoviesByTitle(String title) {
        String lowercaseTitle = title.toLowerCase();

        return movieRepository.findAll()
                .stream()
                .filter(movie -> movie.getTitle() != null && movie.getTitle().toLowerCase().contains(lowercaseTitle))
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }
}
