package com.fh.fantasy_rink.player;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

@Component
public class GoalieService {
    private final GoalieRepository goalieRepository;
    private final String CURRENT_SEASON = "2023-24";

    @Autowired
    public GoalieService(GoalieRepository goalieRepository) {
        this.goalieRepository = goalieRepository;
    }

    public List<Goalie> getGoalies() {
        return goalieRepository.findAllBySeason(CURRENT_SEASON);
    }

    public List<Goalie> getGoaliesByTeam(String team) {
        return goalieRepository.findByTeamAndSeason(team, CURRENT_SEASON);
    }

    public Optional<Goalie> getGoalieByName(String name) {
        return goalieRepository.findByNameAndSeason(name, CURRENT_SEASON);
    }

    public Goalie addGoalie(Goalie goalie) {
        goalie.setSeason(CURRENT_SEASON);
        return goalieRepository.save(goalie);
    }

    public Goalie updateGoalie(Goalie updatedGoalie) {
        Optional<Goalie> existingGoalie = goalieRepository.findByNameAndSeason(updatedGoalie.getName(),
            CURRENT_SEASON);
        if (existingGoalie.isPresent()) {
            Goalie goalieToUpdate = existingGoalie.get();
            goalieToUpdate.setName(updatedGoalie.getName());
            goalieToUpdate.setTeam(updatedGoalie.getTeam());
            return goalieRepository.save(goalieToUpdate);
        }
        else {
            return null;
        }
    }

    @Transactional
    public void deleteGoalie(String name) {
        goalieRepository.deleteByNameAndSeason(name, CURRENT_SEASON);
    }

}
