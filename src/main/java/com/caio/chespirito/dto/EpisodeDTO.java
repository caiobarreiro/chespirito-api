package com.caio.chespirito.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.caio.chespirito.dto.Character.CharacterDTO;
import com.caio.chespirito.model.EpisodeEntity;

public class EpisodeDTO {
    public UUID id;
    public Integer season;
    public Integer episodeNumber;
    public LocalDate airDate;

    public String title;
    public String titleEs;
    public String synopsisPt;

    public List<CharacterSummaryDto> characters;

    public ShowDTO show;

    public static EpisodeDTO of(EpisodeEntity e) {
        EpisodeDTO dto = new EpisodeDTO();
        dto.id = e.getId();
        dto.season = e.getSeason();
        dto.episodeNumber = e.getEpisodeNumber();
        dto.airDate = e.getAirDate();
        dto.title = e.getTitle();
        dto.titleEs = e.getTitleEs();
        dto.synopsisPt = e.getSynopsisPt();
        dto.characters = e.getCharacters()
            .stream()
            .map(CharacterSummaryDto::of)
            .sorted((a, b) -> a.name.compareToIgnoreCase(b.name))
            .collect(Collectors.toList());
        dto.show = ShowDTO.of(e.getShow());
        return dto;
    }
}
