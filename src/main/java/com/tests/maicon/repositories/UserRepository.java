package com.tests.maicon.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tests.maicon.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

}
