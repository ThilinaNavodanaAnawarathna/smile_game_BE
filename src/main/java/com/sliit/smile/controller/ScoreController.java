package com.sliit.smile.controller;

import com.sliit.smile.dto.Result;
import com.sliit.smile.service.ScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/score")
public class ScoreController {
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/top_players")
    public ResponseEntity<?> getTopPlayers() {
        return new ResponseEntity<>(scoreService.getTopPlayers(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getUserScore(@RequestParam Long userId) {
        return new ResponseEntity<>(scoreService.findByUser(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> updateUserScore(@RequestBody Result result) {
        return new ResponseEntity<>(scoreService.updateUserScore(result), HttpStatus.OK);
    }
}
