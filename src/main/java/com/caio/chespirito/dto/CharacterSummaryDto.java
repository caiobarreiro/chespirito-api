package com.caio.chespirito.dto;

import java.util.UUID;

import com.caio.chespirito.model.CharacterEntity;

public class CharacterSummaryDto {
    public UUID id;
    public String name;
    public String originalName;
    
    public static CharacterSummaryDto of(CharacterEntity c) {
        CharacterSummaryDto dto = new CharacterSummaryDto();
        dto.id = c.getId();
        dto.name = c.getName();
        dto.originalName = c.getOriginalName();
        return dto;
    }
}
