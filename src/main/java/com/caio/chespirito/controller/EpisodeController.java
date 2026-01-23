package com.caio.chespirito.controller;

import com.caio.chespirito.dto.Character.CharacterDTO;
import com.caio.chespirito.dto.CreateEpisodeRequest;
import com.caio.chespirito.dto.EpisodeDTO;
import com.caio.chespirito.service.EpisodeService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/episodes")
public class EpisodeController {

    private final EpisodeService service;

    public EpisodeController(EpisodeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EpisodeDTO> getEpisodes(
        @RequestParam(value = "q", required = false) String q,
        @RequestParam(value = "showId", required = false) UUID showId,
        @RequestParam(value = "year", required = false) Integer year,
        @RequestParam(value = "characters", required = false) List<UUID> characters,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "size", required = false) Integer size
    ) {
        return service.getEpisodes(q, showId, year, characters, page, size);
    }
    
    @GetMapping("/{id}")
    public EpisodeDTO get(@PathVariable("id") UUID id) {
        return service.getEpisode(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EpisodeDTO create(@RequestBody CreateEpisodeRequest body) {
        return service.createEpisode(body);
    }

    @PutMapping("/{id}/characters")
    public EpisodeDTO updateCharacters(
        @PathVariable("id") UUID id,
        @RequestBody List<CharacterDTO> characters
    ) {
        return service.updateEpisodeCharacters(id, characters);
    }
}
