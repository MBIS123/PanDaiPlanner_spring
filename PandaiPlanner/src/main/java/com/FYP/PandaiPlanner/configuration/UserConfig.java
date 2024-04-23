package com.FYP.PandaiPlanner.configuration;

import com.FYP.PandaiPlanner.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.FYP.PandaiPlanner.repository.UserRepository;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {

        };
    }
}