package lol.hugoqdesh.kmdb.repositories;

import lol.hugoqdesh.kmdb.entities.Actor;
import lol.hugoqdesh.kmdb.entities.Genre;
import lol.hugoqdesh.kmdb.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByGenres(Genre genre);

    List<Movie> findByReleaseYear(Integer releaseYear);

    List<Movie> findByActors(Actor actor);
}
