package com.fh.fantasy_rink.player;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="goalie_stats")
@IdClass(GoalieID.class)
public class Goalie implements Serializable {
    @Id
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "team")
    private String team;

    @Id
    @Column(name = "season")
    private String season;

    @Column(name = "age")
    private float age;

    @Column(name = "games_played")
    private float gamesPlayed;

    @Column(name = "wins")
    private float wins;

    @Column(name = "losses")
    private float losses;

    @Column(name = "shutouts")
    private float shutouts;

    @Column(name = "goals_against_average")
    private float goalsAgainstAverage;

    @Column(name = "save_percentage")
    private float savePercentage;

    @Column(name = "fantasy_points")
    private float fantasyPoints;

    @Column(name = "projected_fantasy_points")
    private float projectedFantasyPoints;

    public Goalie() {
    }

    public Goalie(String name, String team, String season) {
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

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public float getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(float gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public float getWins() {
        return wins;
    }

    public void setWins(float wins) {
        this.wins = wins;
    }

    public float getLosses() {
        return losses;
    }

    public void setLosses(float losses) {
        this.losses = losses;
    }

    public float getShutouts() {
        return shutouts;
    }

    public void setShutouts(float shutouts) {
        this.shutouts = shutouts;
    }

    public float getGoalsAgainstAverage() {
        return goalsAgainstAverage;
    }

    public void setGoalsAgainstAverage(float goalsAgainstAverage) {
        this.goalsAgainstAverage = goalsAgainstAverage;
    }

    public float getSavePercentage() {
        return savePercentage;
    }

    public void setSavePercentage(float savePercentage) {
        this.savePercentage = savePercentage;
    }

    public float getFantasyPoints() {
        return fantasyPoints;
    }

    public void setFantasyPoints(float fantasyPoints) {
        this.fantasyPoints = fantasyPoints;
    }

    public float getProjectedFantasyPoints() {
        return projectedFantasyPoints;
    }

    public void setProjectedFantasyPoints(float projectedFantasyPoints) {
        this.projectedFantasyPoints = projectedFantasyPoints;
    }
}
