package com.caio.chespirito.controller;

import com.caio.chespirito.dto.Character.CharacterDTO;
import com.caio.chespirito.dto.Character.CharacterListDTO;
import com.caio.chespirito.dto.CreateCharacterRequest;
import com.caio.chespirito.service.CharacterService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/characters")
public class CharacterController {

  private final CharacterService service;

  public CharacterController(CharacterService service) {
    this.service = service;
  }

  @GetMapping
  public List<CharacterListDTO> getCharacters() {
    return service.getCharacters();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CharacterDTO> get(@PathVariable("id") UUID id) {
    return service.getCharacter(id);
  }

  @PostMapping
  public ResponseEntity<CharacterDTO> create(@RequestBody CreateCharacterRequest body) {
    return service.createCharacter(body);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CharacterDTO> update(
      @PathVariable("id") UUID id,
      @RequestBody CreateCharacterRequest body
  ) {
    return service.updateCharacter(id, body);
  }
}
