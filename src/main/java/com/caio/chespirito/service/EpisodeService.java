package com.caio.chespirito.service;

import com.caio.chespirito.dto.Character.CharacterDTO;
import com.caio.chespirito.dto.EpisodeDTO;
import com.caio.chespirito.model.CharacterEntity;
import com.caio.chespirito.repo.CharacterRepository;
import com.caio.chespirito.repo.EpisodeRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EpisodeService {

    private final EpisodeRepository repo;
    private final CharacterRepository characterRepo;

    public EpisodeService(EpisodeRepository repo, CharacterRepository characterRepo) {
        this.repo = repo;
        this.characterRepo = characterRepo;
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
            .orElseGet(() -> ResponseEntity.<EpisodeDTO>notFound().build());
    }

    public ResponseEntity<EpisodeDTO> updateEpisodeCharacters(UUID episodeId, List<CharacterDTO> characters) {
        if (characters == null) {
            return ResponseEntity.<EpisodeDTO>badRequest().build();
        }

        Set<UUID> characterIds = characters.stream()
            .map(character -> character.id)
            .collect(Collectors.toSet());

        if (characterIds.stream().anyMatch(Objects::isNull)) {
            return ResponseEntity.<EpisodeDTO>badRequest().build();
        }

        Optional<EpisodeEntity> episode = repo.findOneWithCharactersAndShow(episodeId);
        if (episode.isEmpty()) {
            return ResponseEntity.<EpisodeDTO>notFound().build();
        }

        Set<CharacterEntity> updatedCharacters = new HashSet<>();
        if (!characterIds.isEmpty()) {
            List<CharacterEntity> foundCharacters = characterRepo.findAllById(characterIds);
            if (foundCharacters.size() != characterIds.size()) {
                return ResponseEntity.<EpisodeDTO>notFound().build();
            }
            updatedCharacters.addAll(foundCharacters);
        }

        EpisodeEntity existingEpisode = episode.get();
        existingEpisode.getCharacters().clear();
        existingEpisode.getCharacters().addAll(updatedCharacters);
        repo.save(existingEpisode);

        return repo.findOneWithCharactersAndShow(episodeId)
            .map(found -> ResponseEntity.ok(EpisodeDTO.of(found)))
            .orElseGet(() -> ResponseEntity.<EpisodeDTO>notFound().build());
    }
}
