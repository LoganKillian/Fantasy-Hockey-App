package com.fh.fantasy_rink.player;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;

@Component
public class SkaterService {
    private final SkaterRepository skaterRepository;
    private final String CURRENT_SEASON = "2023-24";

    @Autowired
    public SkaterService(SkaterRepository skaterRepository) {
        this.skaterRepository = skaterRepository;
    }

    public List<Skater> getSkaters() {
        return skaterRepository.findAllBySeason(CURRENT_SEASON);
    }

    public List<Skater> getSkatersByTeam(String team) {
        return skaterRepository.findByTeamAndSeason(team, CURRENT_SEASON);
    }

    public Optional<Skater> getSkaterByName(String name) {
        return skaterRepository.findByNameAndSeason(name, CURRENT_SEASON);
    }

    public List<Skater> getSkatersByPosition(String position) {
        return skaterRepository.findByPositionAndSeason(position, CURRENT_SEASON);
    }

    public List<Skater> getSkatersByPositionAndTeam(String position, String team) {
        return skaterRepository.findByPositionAndTeamAndSeason(position, team, CURRENT_SEASON);
    }

    public Skater addSkater(Skater skater) {
        skater.setSeason(CURRENT_SEASON);
        return skaterRepository.save(skater);
    }

    public Skater updateSkater(Skater updatedSkater) {
        Optional<Skater> existingSkater = skaterRepository.findByNameAndSeason(updatedSkater.getName(),
            CURRENT_SEASON);
        if (existingSkater.isPresent()) {
            Skater skaterToUpdate = existingSkater.get();
            skaterToUpdate.setName(updatedSkater.getName());
            skaterToUpdate.setTeam(updatedSkater.getTeam());
            skaterToUpdate.setPosition(updatedSkater.getPosition());
            return skaterRepository.save(skaterToUpdate);
        }
        else {
            return null;
        }
    }

    @Transactional
    public void deleteSkater(String name) {
        skaterRepository.deleteByNameAndSeason(name, CURRENT_SEASON);
    }

}
