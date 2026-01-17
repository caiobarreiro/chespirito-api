package com.caio.chespirito.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "original_name")
    private String originalName;

    @ManyToMany(mappedBy = "characters")
    private List<EpisodeEntity> episodes = List.of();
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "actor_id", nullable = false)
    private ActorEntity actor;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOriginalName() { return originalName; }
    public void setOriginalName(String originalName) { this.originalName = originalName; }

    public ActorEntity getActor() { return actor; }
    public void setActor(ActorEntity actor) { this.actor = actor; }
    
    public List<EpisodeEntity> getEpisodes() { return episodes; }
}
