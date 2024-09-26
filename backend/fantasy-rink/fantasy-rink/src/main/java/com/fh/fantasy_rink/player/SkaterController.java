package com.fh.fantasy_rink.player;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/skater")
public class SkaterController {
    private final SkaterService skaterService;

    @Autowired
    public SkaterController(SkaterService skaterService) {
        this.skaterService = skaterService;
    }

    @GetMapping
    public List<Skater> getSkaters(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String team,
        @RequestParam(required = false) String position) {
            if (team != null && position != null) {
                return skaterService.getSkatersByPositionAndTeam(position, team);
            }
            else if (team != null) {
                return skaterService.getSkatersByTeam((team));
            }
            else if (position != null) {
                return skaterService.getSkatersByPosition(position);
            }
            else if (name != null) {
                return skaterService.getSkaterByName(name)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
            }
            else {
                return skaterService.getSkaters();
            }
    }

    @PostMapping
    public ResponseEntity<Skater> addSkater(@RequestBody Skater skater) {
        Skater createdSkater = skaterService.addSkater(skater);
        return new ResponseEntity<>(createdSkater, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Skater> updateSkater(@RequestBody Skater skater) {
        Skater updatedSkater = skaterService.updateSkater(skater);
        if (updatedSkater != null) {
            return new ResponseEntity<>(updatedSkater, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{skaterName}")
    public ResponseEntity<String> deleteSkater(@PathVariable String skaterName) {
        skaterService.deleteSkater(skaterName);
        return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
    }
}
