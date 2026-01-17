package com.caio.chespirito.dto;

import java.time.LocalDate;

public class CreateShowRequest {
  private String name;
  private String nameEs;
  private LocalDate startDate;
  private LocalDate endDate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameEs() {
    return nameEs;
  }

  public void setNameEs(String nameEs) {
    this.nameEs = nameEs;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }
}
