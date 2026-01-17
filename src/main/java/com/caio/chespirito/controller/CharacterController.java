package com.caio.chespirito.controller;

import com.caio.chespirito.dto.Character.CharacterDTO;
import com.caio.chespirito.dto.Character.CharacterListDTO;
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
  
//
//  @PostMapping()
//  @ResponseStatus(HttpStatus.CREATED)
//  public CharacterEntity create(@RequestBody CharacterEntity body) {
//    if (body.getName() != null) {
//      body.setName(body.getName().trim());
//    }
//    return repo.save(body);
//  }
}
