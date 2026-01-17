package com.caio.chespirito.dto;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

import com.caio.chespirito.model.EpisodeEntity;

public class EpisodeSummaryDto {
    UUID id;
    Integer season;
    Integer episodeNumber;
    LocalDate airDate;
    String title;
    String titleEs;
    
    public static EpisodeSummaryDto of(EpisodeEntity e) {
        EpisodeSummaryDto dto = new EpisodeSummaryDto();
        dto.id = e.getId();
        dto.season = e.getSeason();
        dto.episodeNumber = e.getEpisodeNumber();
        dto.airDate = e.getAirDate();
        dto.title = e.getTitle();
        dto.titleEs = e.getTitleEs();
        return dto;
    }
}
