package com.fh.fantasy_rink.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface GoalieRepository extends JpaRepository<Goalie, GoalieID> {
    void deleteByNameAndSeason(String name, String season);
    Optional<Goalie> findByNameAndSeason(String name, String season);
    List<Goalie> findAllBySeason(String season);
    List<Goalie> findByTeamAndSeason(String team, String season);
}
