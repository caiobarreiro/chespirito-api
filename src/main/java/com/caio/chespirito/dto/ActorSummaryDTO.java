package com.caio.chespirito.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.caio.chespirito.model.ActorEntity;

public class ActorSummaryDTO {
    public UUID id;
    public String name;
    public String fullName;
    public LocalDate dob;
    public LocalDate dod;
    
    public static ActorSummaryDTO of(ActorEntity c) {
        ActorSummaryDTO dto = new ActorSummaryDTO();
        dto.id = c.getId();
        dto.name = c.getName();
        dto.fullName = c.getFullName();
        dto.dob = c.getDob();
        dto.dod = c.getDod();
        return dto;
    }
}