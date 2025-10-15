package lol.hugoqdesh.kmdb.repositories;

import lol.hugoqdesh.kmdb.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
