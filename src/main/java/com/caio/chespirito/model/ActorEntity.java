package com.caio.chespirito.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "actors")
public class ActorEntity {

  @Id
  @Column(nullable = false)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(name = "full_name")
  private String fullName;

  private LocalDate dob;
  private LocalDate dod;
  
  @OneToMany(mappedBy = "actor", fetch = FetchType.LAZY)
  private List<CharacterEntity> characters = new ArrayList<>();

  @PrePersist
  void prePersist() {
    if (id == null) id = UUID.randomUUID();
  }

  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }

  public LocalDate getDob() { return dob; }
  public void setDob(LocalDate dob) { this.dob = dob; }

  public LocalDate getDod() { return dod; }
  public void setDod(LocalDate dod) { this.dod = dod; }
  
  public List<CharacterEntity> getCharacters() { return characters; }
  public void setCharacters(List<CharacterEntity> characters) { this.characters = characters; }
}
