package lol.hugoqdesh.kmdb.service;

import lol.hugoqdesh.kmdb.entities.Genre;
import lol.hugoqdesh.kmdb.entities.Movie;
import lol.hugoqdesh.kmdb.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre with id " + id + " not found"));
    }

    public Set<Movie> getMoviesByGenreId(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre with id " + id + " not found"));
        return genre.getMovies();
    }

    public Genre updateGenreName(Long id,  String name) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre with id " + id + " not found"));
        genre.setName(name);
        return genreRepository.save(genre);
    }

    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }
}
