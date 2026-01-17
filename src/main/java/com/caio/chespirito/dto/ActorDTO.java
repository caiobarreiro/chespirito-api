package com.caio.chespirito.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.caio.chespirito.model.ActorEntity;

public class ActorDTO {
    public UUID id;
    public String name;
    public String fullName;
    public LocalDate dob;
    public LocalDate dod;
    public List<CharacterSummaryDto> characters;
    
    public static ActorDTO of(ActorEntity c) {
        ActorDTO dto = new ActorDTO();
        dto.id = c.getId();
        dto.name = c.getName();
        dto.fullName = c.getFullName();
        dto.dob = c.getDob();
        dto.dod = c.getDod();
        dto.characters = c.getCharacters()
            .stream()
            .map(CharacterSummaryDto::of)
            .sorted((a, b) -> a.name.compareToIgnoreCase(b.name))
            .collect(Collectors.toList());
        return dto;
    }
}