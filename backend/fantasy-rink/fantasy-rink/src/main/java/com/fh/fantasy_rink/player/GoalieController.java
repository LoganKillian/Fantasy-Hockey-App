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
@RequestMapping(path = "api/goalie")
public class GoalieController {
    private final GoalieService goalieService;

    @Autowired
    public GoalieController(GoalieService goalieService) {
        this.goalieService = goalieService;
    }

    @GetMapping
    public List<Goalie> getGoalies(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String team) {
            if (team != null) {
                return goalieService.getGoaliesByTeam((team));
            }
            else if (name != null) {
                return goalieService.getGoalieByName(name)
                    .map(Collections::singletonList)
                    .orElse(Collections.emptyList());
            }
            else {
                return goalieService.getGoalies();
            }
    }

    @PostMapping
    public ResponseEntity<Goalie> addGoalie(@RequestBody Goalie goalie) {
        Goalie createdgoalie = goalieService.addGoalie(goalie);
        return new ResponseEntity<>(createdgoalie, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Goalie> updateGoalie(@RequestBody Goalie goalie) {
        Goalie updatedGoalie = goalieService.updateGoalie(goalie);
        if (updatedGoalie != null) {
            return new ResponseEntity<>(updatedGoalie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{goalieName}")
    public ResponseEntity<String> deleteGoalie(@PathVariable String goalieName) {
        goalieService.deleteGoalie(goalieName);
        return new ResponseEntity<>("Player deleted successfully", HttpStatus.OK);
    }
}
