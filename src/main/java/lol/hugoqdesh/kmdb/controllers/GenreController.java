package lol.hugoqdesh.kmdb.controllers;

import jakarta.validation.Valid;
import lol.hugoqdesh.kmdb.dto.GenreRequestDTO;
import lol.hugoqdesh.kmdb.dto.GenreResponseDTO;
import lol.hugoqdesh.kmdb.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<GenreResponseDTO> createGenre(@Valid @RequestBody GenreRequestDTO dto) {
        return new ResponseEntity<>(genreService.createGenre(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreResponseDTO>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> getGenreById(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenreResponseDTO> updateGenre(@PathVariable Long id, @RequestBody GenreRequestDTO dto) {
        return ResponseEntity.ok(genreService.updateGenre(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id, @RequestParam(defaultValue = "false") boolean force) {
        try {
            genreService.deleteGenreById(id, force);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
