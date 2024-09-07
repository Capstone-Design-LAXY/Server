package com.group.laxyapp.domain.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nickName);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long userId);

    List<User> findAll();

    boolean existsByEmail(String email);
}