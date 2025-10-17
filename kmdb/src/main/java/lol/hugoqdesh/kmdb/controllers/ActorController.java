package lol.hugoqdesh.kmdb.controllers;

import lol.hugoqdesh.kmdb.dto.ActorRequestDTO;
import lol.hugoqdesh.kmdb.dto.ActorResponseDTO;
import lol.hugoqdesh.kmdb.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<ActorResponseDTO> createActor(@RequestBody ActorRequestDTO dto) {
        return new ResponseEntity<>(actorService.createActor(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActorResponseDTO>> getActors(@RequestParam(required = false) String name) {
        if(name != null) {
            return ResponseEntity.ok(actorService.getActorsByName(name));
        }

        return ResponseEntity.ok(actorService.getAllActors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> getActorById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ActorResponseDTO> updateActor(@PathVariable Long id, @RequestBody ActorRequestDTO dto) {
        return ResponseEntity.ok(actorService.updateActor(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActorById(@PathVariable Long id) {
        actorService.deleteActorById(id);
        return ResponseEntity.noContent().build();
    }
}
