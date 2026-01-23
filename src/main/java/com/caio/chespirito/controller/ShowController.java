package com.caio.chespirito.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.caio.chespirito.dto.CreateShowRequest;
import com.caio.chespirito.dto.ShowDTO;
import com.caio.chespirito.service.ShowService;

@RestController
@RequestMapping("/shows")
public class ShowController {

  private final ShowService service;

  public ShowController(ShowService service) {
    this.service = service;
  }

  @GetMapping
  public List<ShowDTO> list() {
    return service.getShows();
  }

  @GetMapping("/{id}")
  public ShowDTO get(@PathVariable("id") UUID id) {
    return service.getShow(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ShowDTO create(@RequestBody CreateShowRequest body) {
    return service.createShow(body);
  }

  @PutMapping("/{id}")
  public ShowDTO update(
      @PathVariable("id") UUID id,
      @RequestBody CreateShowRequest body
  ) {
    return service.updateShow(id, body);
  }
}
