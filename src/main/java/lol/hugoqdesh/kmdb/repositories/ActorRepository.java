package lol.hugoqdesh.kmdb.repositories;

import lol.hugoqdesh.kmdb.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findByName(String name);
}
