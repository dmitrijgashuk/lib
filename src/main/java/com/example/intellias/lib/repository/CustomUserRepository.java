package com.example.intellias.lib.repository;

import com.example.intellias.lib.domain.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser,Long> {
    Optional<CustomUser> getSecurityUserByLogin(String login);
}
