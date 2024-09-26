package com.fh.fantasy_rink.player;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name="skater_stats")
@IdClass(SkaterID.class)
public class Skater implements Serializable {
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

    @Column(name = "position")
    private String position;

    @Column(name = "games_played")
    private float gamesPlayed;

    @Column(name = "goals")
    private float goals;

    @Column(name = "assists")
    private float assists;

    @Column(name = "points")
    private float points;

    @Column(name = "penalty_minutes")
    private float penaltyMinutes;

    @Column(name = "power_play_points")
    private float powerPlayPoints;

    @Column(name = "shots")
    private float shots;

    @Column(name = "shooting_percentage")
    private float shootingPercentage;

    @Column(name = "hits")
    private float hits;

    @Column(name = "blocked_shots")
    private float blockedShots;

    @Column(name = "predicted_class")
    private String predictedClass;

    @Column(name = "fantasy_points")
    private float fantasyPoints;

    @Column(name = "projected_fantasy_points")
    private float projectedFantasyPoints;

    public Skater() {
    }

    public Skater(String name, String team, String season) {
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public float getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(float gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public float getGoals() {
        return goals;
    }

    public void setGoals(float goals) {
        this.goals = goals;
    }

    public float getAssists() {
        return assists;
    }

    public void setAssists(float assists) {
        this.assists = assists;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public float getPenaltyMinutes() {
        return penaltyMinutes;
    }

    public void setPenaltyMinutes(float penaltyMinutes) {
        this.penaltyMinutes = penaltyMinutes;
    }

    public float getPowerPlayPoints() {
        return powerPlayPoints;
    }

    public void setPowerPlayPoints(float powerPlayPoints) {
        this.powerPlayPoints = powerPlayPoints;
    }

    public float getShots() {
        return shots;
    }

    public void setShots(float shots) {
        this.shots = shots;
    }

    public float getShootingPercentage() {
        return shootingPercentage;
    }

    public void setShootingPercentage(float shootingPercentage) {
        this.shootingPercentage = shootingPercentage;
    }

    public float getHits() {
        return hits;
    }

    public void setHits(float hits) {
        this.hits = hits;
    }

    public float getBlockedShots() {
        return blockedShots;
    }

    public void setBlockedShots(float blockedShots) {
        this.blockedShots = blockedShots;
    }

    public String getPredictedClass() {
        return predictedClass;
    }

    public void setPredictedClass(String predictedClass) {
        this.predictedClass = predictedClass;
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
