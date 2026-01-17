package com.caio.chespirito.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.caio.chespirito.model.ShowEntity;

public class ShowDTO{
    public UUID id;
    public String name;
    public String nameEs;
    public LocalDate startDate;
    public LocalDate endDate;

    public static ShowDTO of(ShowEntity s) {
        if (s == null) return null;
        ShowDTO dto = new ShowDTO();
        dto.id = s.getId();
        dto.name = s.getName();
        dto.nameEs = s.getNameEs();
        dto.startDate = s.getStartDate();
        dto.endDate = s.getEndDate();
        return dto;
    }
}
