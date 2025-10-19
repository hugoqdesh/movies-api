package lol.hugoqdesh.kmdb.repositories;

import lol.hugoqdesh.kmdb.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
