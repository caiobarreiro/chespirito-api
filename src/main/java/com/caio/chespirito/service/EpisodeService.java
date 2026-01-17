package com.caio.chespirito.service;

import com.caio.chespirito.dto.EpisodeDTO;
import com.caio.chespirito.model.EpisodeEntity;
import com.caio.chespirito.repo.EpisodeRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EpisodeService {

    private final EpisodeRepository repo;

    public EpisodeService(EpisodeRepository repo) {
        this.repo = repo;
    }

    public List<EpisodeDTO> getEpisodes(String q, UUID showId) {
        boolean hasQuery = q != null && !q.trim().isEmpty();

        // Sem q -> lista tudo (com show + characters)
        if (!hasQuery) {
            return repo.findAllWithCharactersAndShow(showId)
                    .stream()
                    .map(EpisodeDTO::of)
                    .collect(Collectors.toList());
        }

        // Com q -> busca ids ordenados por relevância (full-text + fuzzy), filtrando showId se vier
        List<UUID> ids = repo.searchIdsByTextAndShow(q, showId);

        // Fallback: se não achou por texto, retorna tudo do show (ou global se showId null)
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        // Carrega episódios + show + characters em batch
        List<EpisodeDTO> loaded = repo.findByIdInWithCharactersAndShow(ids)
                .stream()
                .map(EpisodeDTO::of)
                .collect(Collectors.toList());

        // (Opcional mas recomendado) manter a ordem de relevância do resultado de ids
        Map<UUID, Integer> order = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) order.put(ids.get(i), i);

        loaded.sort(Comparator.comparingInt(d -> order.getOrDefault(d.id, Integer.MAX_VALUE)));

        return loaded;
    }
    
    public ResponseEntity<EpisodeDTO> getEpisode(UUID episodeId) {
        return repo.findOneWithCharactersAndShow(episodeId)
            .map(a -> ResponseEntity.ok(EpisodeDTO.of(a)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
