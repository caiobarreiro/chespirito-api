package com.caio.chespirito.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.caio.chespirito.dto.ActorSummaryDTO;
import com.caio.chespirito.dto.Character.CharacterDTO;
import com.caio.chespirito.dto.Character.CharacterListDTO;
import com.caio.chespirito.dto.CreateCharacterRequest;
import com.caio.chespirito.model.CharacterEntity;
import com.caio.chespirito.repo.CharacterRepository;
import com.caio.chespirito.utils.Utils;

@Service
public class CharacterService {

    private final CharacterRepository repo;

    public CharacterService(CharacterRepository repo) {
        this.repo = repo;
    }
    
    public List<CharacterListDTO> getCharacters() {
        return repo.findAll()
            .stream()
            .map(a -> ResponseEntity.ok(CharacterListDTO.of(a)).getBody())
            .toList();
    }
    
    public ResponseEntity<CharacterDTO> getCharacter(UUID id) {
        return repo.findWithActorById(id)
            .map(a -> ResponseEntity.ok(CharacterDTO.of(a)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<CharacterDTO> createCharacter(CreateCharacterRequest body) {
        if (body.getActor() == null) {
            return ResponseEntity.badRequest().build();
        }

        CharacterEntity entity = new CharacterEntity();
        entity.setName(Utils.normalize(body.getName()));
        entity.setOriginalName(Utils.normalize(body.getOriginalName()));
        entity.setActor(body.getActor());
        CharacterEntity saved = repo.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(CharacterDTO.of(saved));
    }

    public ResponseEntity<CharacterDTO> updateCharacter(UUID id, CreateCharacterRequest body) {
        if (body.getId() == null || !body.getId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }
        if (body.getActor() == null) {
            return ResponseEntity.badRequest().build();
        }

        return repo.findById(id)
            .map(existing -> {
                existing.setName(Utils.normalize(body.getName()));
                existing.setOriginalName(Utils.normalize(body.getOriginalName()));
                existing.setActor(body.getActor());
                CharacterEntity saved = repo.save(existing);
                CharacterDTO dto = CharacterDTO.of(saved);
                dto.actor = ActorSummaryDTO.of(body.getActor());
                return ResponseEntity.ok(dto);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
