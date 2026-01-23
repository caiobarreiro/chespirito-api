package com.caio.chespirito.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            .map(CharacterListDTO::of)
            .toList();
    }
    
    public CharacterDTO getCharacter(UUID id) {
        return repo.findWithActorById(id)
            .map(CharacterDTO::of)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found"));
    }

    public CharacterDTO createCharacter(CreateCharacterRequest body) {
        if (body == null || body.getActor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Character actor is required");
        }

        CharacterEntity entity = new CharacterEntity();
        entity.setName(Utils.normalize(body.getName()));
        entity.setOriginalName(Utils.normalize(body.getOriginalName()));
        entity.setActor(body.getActor());
        CharacterEntity saved = repo.save(entity);
        return CharacterDTO.of(saved);
    }

    public CharacterDTO updateCharacter(UUID id, CreateCharacterRequest body) {
        if (body == null || body.getId() == null || !body.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Character id mismatch");
        }
        if (body.getActor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Character actor is required");
        }

        return repo.findById(id)
            .map(existing -> {
                existing.setName(Utils.normalize(body.getName()));
                existing.setOriginalName(Utils.normalize(body.getOriginalName()));
                existing.setActor(body.getActor());
                CharacterEntity saved = repo.save(existing);
                return repo.findWithActorById(saved.getId())
                    .map(CharacterDTO::of)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found"));
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found"));
    }
}
