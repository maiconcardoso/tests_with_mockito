package com.tests.maicon.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tests.maicon.domain.User;
import com.tests.maicon.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void startDB() {
        User u1 = new User(null, "Maicon", "maicon@gmail.com", "124234");
        User u2 = new User(null, "André", "andre@gmail.com", "1dsfsd234");

        repository.saveAll(List.of(u1, u2));
    }

    

}
