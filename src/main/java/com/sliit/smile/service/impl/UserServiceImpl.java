package com.sliit.smile.service.impl;

import com.sliit.smile.dto.User;
import com.sliit.smile.enumz.Role;
import com.sliit.smile.model.Score;
import com.sliit.smile.repository.ScoreRepository;
import com.sliit.smile.repository.UserRepository;
import com.sliit.smile.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ScoreRepository scoreRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ScoreRepository scoreRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.scoreRepository = scoreRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) throws Exception {
        try {
            com.sliit.smile.model.User userModel = modelMapper.map(user, com.sliit.smile.model.User.class);
            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
            userModel.setCreateTime(LocalDateTime.now());
            userModel.setRole(Role.USER);
            com.sliit.smile.model.User save = userRepository.save(userModel);
            if (save.getId() != null)
                scoreRepository.save(Score.builder().score(0).user(save).build());
            return modelMapper.map(save, User.class);
        } catch (Exception e) {
            log.error("Error user create : Error: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {

        Optional<com.sliit.smile.model.User> byUsername = userRepository.findByUsername(username);

        return byUsername.map(user -> User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .createTime(user.getCreateTime())
                .role(user.getRole())
                .build());

    }
}
