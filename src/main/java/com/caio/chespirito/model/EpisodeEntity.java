package com.caio.chespirito.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "episodes", uniqueConstraints = {
    @UniqueConstraint(name = "uq_episodes_season_number", columnNames = { "season", "episode_number" })
})
public class EpisodeEntity {

  @Id
  @GeneratedValue
  @Column(columnDefinition = "uuid")
  private UUID id;

  @Column(nullable = false)
  private Integer season;

  @Column(name = "episode_number", nullable = false)
  private Integer episodeNumber;

  @Column(name = "air_date")
  private LocalDate airDate;

  // -----------------
  // Titles
  // -----------------

  @Column(nullable = false)
  private String title; // PT

  @Column(name = "title_es", nullable = false)
  private String titleEs; // ES

  // -----------------
  // Synopses
  // -----------------

  @Column(name = "synopsis_pt", nullable = false, columnDefinition = "text")
  private String synopsisPt;

  @Column(name = "synopsis_es", nullable = false, columnDefinition = "text")
  private String synopsisEs;

  /**
   * PostgreSQL generated column (tsvector)
   * Not insertable / not updatable
   */
  @Column(name = "search_vector", columnDefinition = "tsvector", insertable = false, updatable = false)
  private String searchVector;

  @ManyToMany
  @JoinTable(name = "episode_characters", joinColumns = @JoinColumn(name = "episode_id"), inverseJoinColumns = @JoinColumn(name = "character_id"))
  private java.util.Set<CharacterEntity> characters = new java.util.HashSet<>();

  public java.util.Set<CharacterEntity> getCharacters() {
    return characters;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "show_id")
  private ShowEntity show;

  public ShowEntity getShow() {
    return show;
  }

  public void setShow(ShowEntity show) {
    this.show = show;
  }

  // -----------------
  // Getters / Setters
  // -----------------

  public UUID getId() {
    return id;
  }

  public Integer getSeason() {
    return season;
  }

  public void setSeason(Integer season) {
    this.season = season;
  }

  public Integer getEpisodeNumber() {
    return episodeNumber;
  }

  public void setEpisodeNumber(Integer episodeNumber) {
    this.episodeNumber = episodeNumber;
  }

  public LocalDate getAirDate() {
    return airDate;
  }

  public void setAirDate(LocalDate airDate) {
    this.airDate = airDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitleEs() {
    return titleEs;
  }

  public void setTitleEs(String titleEs) {
    this.titleEs = titleEs;
  }

  public String getSynopsisPt() {
    return synopsisPt;
  }

  public void setSynopsisPt(String synopsisPt) {
    this.synopsisPt = synopsisPt;
  }

  public String getSynopsisEs() {
    return synopsisEs;
  }

  public void setSynopsisEs(String synopsisEs) {
    this.synopsisEs = synopsisEs;
  }

  public String getSearchVector() {
    return searchVector;
  }
}
