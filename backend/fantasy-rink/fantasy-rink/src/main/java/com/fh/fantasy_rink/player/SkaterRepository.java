package com.fh.fantasy_rink.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface SkaterRepository extends JpaRepository<Skater, SkaterID> {
    void deleteByNameAndSeason(String name, String season);
    Optional<Skater> findByNameAndSeason(String name, String season);
    List<Skater> findAllBySeason(String season);
    List<Skater> findByTeamAndSeason(String team, String season);
    List<Skater> findByPositionAndSeason(String position, String season);
    List<Skater> findByPositionAndTeamAndSeason(String position, String team, String season);
}
