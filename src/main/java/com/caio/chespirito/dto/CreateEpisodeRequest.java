package com.caio.chespirito.dto;

import java.time.LocalDate;

public class CreateEpisodeRequest {
    private ShowDTO show;
    private Integer episodeNumber;
    private Integer season;
    private LocalDate airDate;
    private String title;
    private String titleES;
    private String synopsisPT;
    private String synopsisEs;

    public ShowDTO getShow() {
        return show;
    }

    public void setShow(ShowDTO show) {
        this.show = show;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
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

    public String getTitleES() {
        return titleES;
    }

    public void setTitleES(String titleES) {
        this.titleES = titleES;
    }

    public String getSynopsisPT() {
        return synopsisPT;
    }

    public void setSynopsisPT(String synopsisPT) {
        this.synopsisPT = synopsisPT;
    }

    public String getSynopsisEs() {
        return synopsisEs;
    }

    public void setSynopsisEs(String synopsisEs) {
        this.synopsisEs = synopsisEs;
    }
}
