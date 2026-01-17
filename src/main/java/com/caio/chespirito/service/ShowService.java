package com.caio.chespirito.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.caio.chespirito.dto.CreateShowRequest;
import com.caio.chespirito.dto.ShowDTO;
import com.caio.chespirito.model.ShowEntity;
import com.caio.chespirito.repo.ShowRepository;
import com.caio.chespirito.utils.Utils;

@Service
public class ShowService {

  private final ShowRepository repo;

  public ShowService(ShowRepository repo) {
    this.repo = repo;
  }

  public List<ShowDTO> getShows() {
    return repo.findAll()
        .stream()
        .map(s -> ResponseEntity.ok(ShowDTO.of(s)).getBody())
        .toList();
  }

  public ResponseEntity<ShowDTO> getShow(UUID id) {
    return repo.findById(id)
        .map(s -> ResponseEntity.ok(ShowDTO.of(s)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  public ShowDTO createShow(CreateShowRequest body) {
    ShowEntity entity = new ShowEntity();
    entity.setName(Utils.normalize(body.getName()));
    entity.setNameEs(Utils.normalize(body.getNameEs()));
    entity.setStartDate(body.getStartDate());
    entity.setEndDate(body.getEndDate());
    return ShowDTO.of(repo.save(entity));
  }
}
