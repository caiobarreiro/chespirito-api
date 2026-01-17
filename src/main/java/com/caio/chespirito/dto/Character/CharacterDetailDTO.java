package com.caio.chespirito.dto.Character;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.caio.chespirito.dto.ActorSummaryDTO;
import com.caio.chespirito.dto.EpisodeSummaryDto;
import com.caio.chespirito.model.CharacterEntity;

public class CharacterDetailDTO {
    public UUID id;
    public String name;
    public String originalName;
    public List<EpisodeSummaryDto> episodes;
    public ActorSummaryDTO actor;

    public static CharacterDetailDTO of(CharacterEntity c) {
        CharacterDetailDTO dto = new CharacterDetailDTO();
        dto.id = c.getId();
        dto.name = c.getName();
        dto.originalName = c.getOriginalName();
        
        dto.actor = Optional.ofNullable(c.getActor())
            .map(ActorSummaryDTO::of)
            .orElse(null);
        
        dto.episodes = Optional.ofNullable(c.getEpisodes())
            .orElse(List.of())
            .stream()
            .map(EpisodeSummaryDto::of)
            .toList();
        return dto;
    }
}
