package com.example.libraryIt;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllByUsernameAndPassword(String username, String password);
    User findOneById(Long id);
    User findByUsernameContainingIgnoreCase(String username);
}