package com.caio.chespirito.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.caio.chespirito.dto.ActorDTO;
import com.caio.chespirito.model.ActorEntity;
import com.caio.chespirito.repo.ActorRepository;

@Service
public class ActorService {

    private final ActorRepository repo;

    public ActorService(ActorRepository repo) {
        this.repo = repo;
    }
    
    public List<ActorDTO> getActors() {
        return repo.findAll()
            .stream()
            .map(a -> ResponseEntity.ok(ActorDTO.of(a)).getBody())
            .toList();
    }
    
    public ResponseEntity<ActorDTO> getActor(UUID id) {
        return repo.findWithCharactersById(id)
            .map(a -> ResponseEntity.ok(ActorDTO.of(a)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ActorDTO createActor(ActorEntity body) {
        body.setId(null);
        if (body.getName() != null) {
            body.setName(body.getName().trim());
        }
        return ActorDTO.of(repo.save(body));
    }
}
