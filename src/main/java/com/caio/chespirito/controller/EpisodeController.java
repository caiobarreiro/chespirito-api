package com.caio.chespirito.controller;

import com.caio.chespirito.dto.EpisodeDTO;
import com.caio.chespirito.model.EpisodeEntity;
import com.caio.chespirito.service.EpisodeService;

import org.springframework.http.ResponseEntity;
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
        @RequestParam(value = "showId", required = false) UUID showId
    ) {
        return service.getEpisodes(q, showId);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EpisodeDTO> get(@PathVariable("id") UUID id) {
        return service.getEpisode(id);
    }
}
