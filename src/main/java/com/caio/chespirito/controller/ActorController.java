package com.caio.chespirito.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caio.chespirito.dto.ActorDTO;
import com.caio.chespirito.model.ActorEntity;
import com.caio.chespirito.service.ActorService;

@RestController
public class ActorController {
  
  private final ActorService service;

  public ActorController(ActorService service) {
    this.service = service;
  }

  @GetMapping("/actors")
  public List<ActorDTO> list() {
    return service.getActors();
  }
  
  @GetMapping("/actors/{id}")
  public ResponseEntity<ActorDTO> get(@PathVariable("id") UUID id) {
    return service.getActor(id);
  }

  @PostMapping("/actors")
  @ResponseStatus(HttpStatus.CREATED)
  public ActorDTO create(@RequestBody ActorEntity body) {
    return service.createActor(body);
  }

  @PutMapping("/actors/{id}")
  public ResponseEntity<ActorDTO> update(
      @PathVariable("id") UUID id,
      @RequestBody ActorEntity body
  ) {
    return service.updateActor(id, body);
  }
}
