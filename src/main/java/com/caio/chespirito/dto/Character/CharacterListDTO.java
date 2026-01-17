package com.caio.chespirito.dto.Character;

import java.util.UUID;

import com.caio.chespirito.model.CharacterEntity;

public class CharacterListDTO {
    public UUID id;
    public String name;
    public String originalName;

    public static CharacterListDTO of(CharacterEntity c) {
        CharacterListDTO dto = new CharacterListDTO();
        dto.id = c.getId();
        dto.name = c.getName();
        dto.originalName = c.getOriginalName();
        return dto;
    }
}
