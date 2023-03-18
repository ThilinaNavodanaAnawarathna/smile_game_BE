package com.sliit.smile.service.impl;

import com.sliit.smile.dto.User;
import com.sliit.smile.enumz.Role;
import com.sliit.smile.repository.UserRepository;
import com.sliit.smile.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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
            return modelMapper.map(userRepository.save(userModel), User.class);
        } catch (Exception e) {
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
