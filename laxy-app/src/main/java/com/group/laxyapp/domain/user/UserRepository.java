package com.group.laxyapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nick_name);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long user_id);

    boolean existsByEmail(String email);
}