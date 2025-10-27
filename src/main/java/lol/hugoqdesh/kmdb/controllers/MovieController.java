package lol.hugoqdesh.kmdb.controllers;

import jakarta.validation.Valid;
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
    public ResponseEntity<MovieResponseDTO> createMovie(@Valid @RequestBody MovieRequestDTO dto) {
        return new ResponseEntity<>(movieService.createMovie(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies(@RequestParam(required = false) Long genre, @RequestParam(required = false) Integer year, @RequestParam(required = false) Long actor, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        if(genre != null) {
            return ResponseEntity.ok(movieService.getMovieByGenreId(genre));
        }
        if(year != null) {
            return ResponseEntity.ok(movieService.getMovieByReleaseYear(year));
        }
        if(actor != null) {
            return ResponseEntity.ok(movieService.getMovieByActorId(actor));
        }

        return ResponseEntity.ok(movieService.getAllMovies(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/{movieId}/actors")
    public ResponseEntity<List<ActorResponseDTO>> getActorsByMovieId(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.getActorByMovieId(movieId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponseDTO>> searchMovies(@RequestParam String title) {
        return ResponseEntity.ok(movieService.searchMoviesByTitle(title));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> updateGenre(@PathVariable Long id, @RequestBody MovieRequestDTO dto) {
        return ResponseEntity.ok(movieService.updateMovie(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long id,  @RequestParam(defaultValue = "false") boolean force) {
        try {
            movieService.deleteMovie(id, force);
            return ResponseEntity.noContent().build();

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
