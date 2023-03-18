package com.sliit.smile.repository;

import com.sliit.smile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAllByUsername(String username);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

}
