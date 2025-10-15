package lol.hugoqdesh.kmdb.service;

import lol.hugoqdesh.kmdb.entities.Actor;
import lol.hugoqdesh.kmdb.entities.Genre;
import lol.hugoqdesh.kmdb.entities.Movie;
import lol.hugoqdesh.kmdb.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(Long id, String title, Integer releaseYear, Integer duration, Set<Genre> genre, Set<Actor> actors) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setDuration(duration);
        movie.setGenres(genre);
        movie.setActors(actors);
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre with id " + id + " not found"));
    }

    public List<Movie> getMovieByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public List<Movie> getMovieByReleaseYear(Integer releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    public Set<Actor> getAllActorsByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Actor with id " + movieId + " not found"));
        return movie.getActors();
    }

    public Movie updateMovie(Long id, String title, Integer releaseYear, Integer duration, Set<Genre> genre, Set<Actor> actors) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Actor with id " + id + " not found"));
        movie.setTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setDuration(duration);
        movie.setGenres(genre);
        movie.setActors(actors);
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
