package lol.hugoqdesh.kmdb.service;

import lol.hugoqdesh.kmdb.dto.GenreRequestDTO;
import lol.hugoqdesh.kmdb.dto.GenreResponseDTO;
import lol.hugoqdesh.kmdb.entities.Genre;
import lol.hugoqdesh.kmdb.exception.ResourceNotFoundException;
import lol.hugoqdesh.kmdb.mapper.GenreMapper;
import lol.hugoqdesh.kmdb.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    public GenreResponseDTO createGenre(GenreRequestDTO dto) {
        Genre genre = genreMapper.toEntity(dto);
        return genreMapper.toDTO(genreRepository.save(genre));
    }

    @Transactional
    public List<GenreResponseDTO> getAllGenres() {
        return genreRepository.findAll().stream().map(genreMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public GenreResponseDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre with id " + id + " not found"));
        return  genreMapper.toDTO(genre);
    }

    @Transactional
    public GenreResponseDTO updateGenre(Long id, GenreRequestDTO dto) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre with id " + id + " not found"));
        genre.setName(dto.getName());
        return genreMapper.toDTO(genre);
    }

    public void deleteGenreById(Long id) {
        genreRepository.deleteById(id);
    }
}
