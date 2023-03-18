package com.sliit.smile.service;

import com.sliit.smile.dto.Result;
import com.sliit.smile.dto.ScoreDto;
import com.sliit.smile.dto.TopPlayer;

import java.util.List;

public interface ScoreService {
    List<TopPlayer> getTopPlayers();

    ScoreDto findByUser(Long userId);

    ScoreDto updateUserScore(Result result);
}
