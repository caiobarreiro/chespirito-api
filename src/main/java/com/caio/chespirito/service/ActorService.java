package com.caio.chespirito.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            .map(ActorDTO::of)
            .toList();
    }
    
    public ActorDTO getActor(UUID id) {
        return repo.findWithCharactersById(id)
            .map(ActorDTO::of)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found"));
    }

    public ActorDTO createActor(ActorEntity body) {
        if (body == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Actor payload is required");
        }
        body.setId(null);
        if (body.getName() != null) {
            body.setName(body.getName().trim());
        }
        return ActorDTO.of(repo.save(body));
    }

    public ActorDTO updateActor(UUID id, ActorEntity body) {
        if (body == null || body.getId() == null || !body.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Actor id mismatch");
        }

        return repo.findById(id)
            .map(existing -> {
                existing.setName(body.getName());
                existing.setFullName(body.getFullName());
                existing.setDob(body.getDob());
                existing.setDod(body.getDod());
                if (existing.getName() != null) {
                    existing.setName(existing.getName().trim());
                }
                ActorEntity saved = repo.save(existing);
                return repo.findWithCharactersById(saved.getId())
                    .map(ActorDTO::of)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found"));
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found"));
    }
}
