package lol.hugoqdesh.kmdb.repositories;

import lol.hugoqdesh.kmdb.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
