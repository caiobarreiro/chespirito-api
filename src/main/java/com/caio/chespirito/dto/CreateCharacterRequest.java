package com.caio.chespirito.dto;

public class CreateCharacterRequest {
  private java.util.UUID id;
  private String name;
  private String originalName;
  private com.caio.chespirito.model.ActorEntity actor;

  public java.util.UUID getId() {
    return id;
  }

  public void setId(java.util.UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOriginalName() {
    return originalName;
  }

  public void setOriginalName(String originalName) {
    this.originalName = originalName;
  }

  public com.caio.chespirito.model.ActorEntity getActor() {
    return actor;
  }

  public void setActor(com.caio.chespirito.model.ActorEntity actor) {
    this.actor = actor;
  }
}
