package com.fh.fantasy_rink.player;

import java.io.Serializable;
import java.util.Objects;

public class SkaterID implements Serializable {
    private String name;
    private String team;
    private String season;

    public SkaterID() {}

    public SkaterID(String name, String team, String season) {
        this.name = name;
        this.team = team;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkaterID skaterId = (SkaterID) o;
        return name.equals(skaterId.name) &&
           team.equals(skaterId.team) &&
           season.equals(skaterId.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, season);
    }

}
