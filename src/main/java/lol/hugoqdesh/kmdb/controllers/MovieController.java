package lol.hugoqdesh.kmdb.controllers;

import lol.hugoqdesh.kmdb.dto.ActorResponseDTO;
import lol.hugoqdesh.kmdb.dto.MovieRequestDTO;
import lol.hugoqdesh.kmdb.dto.MovieResponseDTO;
import lol.hugoqdesh.kmdb.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody MovieRequestDTO dto) {
        return new ResponseEntity<>(movieService.createMovie(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies(@RequestParam(required = false) Long genre, @RequestParam(required = false) Integer year, @RequestParam(required = false) Long actor) {

        if(genre != null) {
            return ResponseEntity.ok(movieService.getMovieByGenreId(genre));
        }
        if(year != null) {
            return ResponseEntity.ok(movieService.getMovieByReleaseYear(year));
        }
        if(actor != null) {
            return ResponseEntity.ok(movieService.getMovieByActorId(actor));
        }

        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/{movieId}/actors")
    public ResponseEntity<List<ActorResponseDTO>> getActorsByMovieId(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.getActorByMovieId(movieId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateGenre(@PathVariable Long id, @RequestBody MovieRequestDTO dto) {
        return ResponseEntity.ok(movieService.updateMovie(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
