package com.caio.chespirito.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.caio.chespirito.dto.Character.CharacterDTO;
import com.caio.chespirito.dto.Character.CharacterListDTO;
import com.caio.chespirito.repo.CharacterRepository;

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

}
