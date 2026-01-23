package com.caio.chespirito.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        .map(ShowDTO::of)
        .toList();
  }

  public ShowDTO getShow(UUID id) {
    return repo.findById(id)
        .map(ShowDTO::of)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found"));
  }

  public ShowDTO createShow(CreateShowRequest body) {
    if (body == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show payload is required");
    }
    ShowEntity entity = new ShowEntity();
    entity.setName(Utils.normalize(body.getName()));
    entity.setNameEs(Utils.normalize(body.getNameEs()));
    entity.setStartDate(body.getStartDate());
    entity.setEndDate(body.getEndDate());
    return ShowDTO.of(repo.save(entity));
  }

  public ShowDTO updateShow(UUID id, CreateShowRequest body) {
    if (body == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Show payload is required");
    }
    return repo.findById(id)
        .map(existing -> {
          existing.setName(Utils.normalize(body.getName()));
          existing.setNameEs(Utils.normalize(body.getNameEs()));
          existing.setStartDate(body.getStartDate());
          existing.setEndDate(body.getEndDate());
          ShowEntity saved = repo.save(existing);
          return ShowDTO.of(saved);
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found"));
  }
}
