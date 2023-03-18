package com.sliit.smile.service.impl;

import com.sliit.smile.dto.Result;
import com.sliit.smile.dto.ScoreDto;
import com.sliit.smile.dto.TopPlayer;
import com.sliit.smile.model.DifficultyLevel;
import com.sliit.smile.model.Score;
import com.sliit.smile.model.User;
import com.sliit.smile.repository.DifficultyLevelRepository;
import com.sliit.smile.repository.ScoreRepository;
import com.sliit.smile.repository.UserRepository;
import com.sliit.smile.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ScoreServiceImpl implements ScoreService {
    private final ScoreRepository scoreRepository;
    private final DifficultyLevelRepository difficultyLevelRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository, DifficultyLevelRepository difficultyLevelRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.scoreRepository = scoreRepository;
        this.difficultyLevelRepository = difficultyLevelRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<TopPlayer> getTopPlayers() {
        try {
            List<Score> scores = scoreRepository.findFirst10ByScoreOrderByScoreIdDesc();
            return scores.stream().map(score -> TopPlayer.builder().name(score.getUser().getName()).score(score.getScore()).build()).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error get top list : Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ScoreDto findByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent())
            throw new RuntimeException("Invalid user");
        return modelMapper.map(scoreRepository.findByUser(user.get()), ScoreDto.class);
    }

    @Override
    public ScoreDto updateUserScore(Result result) {
        try {
            Score score = scoreRepository.findByUser(modelMapper.map(result.getUser(), User.class));
            if (score == null)
                throw new RuntimeException("User is invalid");
            Optional<DifficultyLevel> difficultyLevel = difficultyLevelRepository.findById(result.getDifficultyLevel());
            if (difficultyLevel.isPresent()) {
                DifficultyLevel level = difficultyLevel.get();
                if (result.getIsCorrect()) {
                    score.setScore(score.getScore() + level.getPoint());
                    return modelMapper.map(scoreRepository.save(score), ScoreDto.class);
                } else {
                    score.setScore(Math.max(score.getScore() - 5, 0));
                    return modelMapper.map(scoreRepository.save(score), ScoreDto.class);
                }
            } else {
                throw new RuntimeException("Difficulty Level is invalid");
            }
        } catch (Exception e) {
            log.error("Error update user score : Error: {}", e.getMessage());
            throw e;
        }
    }
}
