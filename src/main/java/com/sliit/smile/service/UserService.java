package com.sliit.smile.service;

import com.sliit.smile.dto.User;

import java.util.Optional;

public interface UserService {
     User save(User user) throws Exception;
     Optional<User> findByUsername(String username);
}
