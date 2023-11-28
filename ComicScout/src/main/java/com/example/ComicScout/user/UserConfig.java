package com.example.ComicScout.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args ->{
            User mariam= new User(
                    "Mariam",
                    "mariam.jamal@gmail.com"
            );
            User alex= new User(
                    "Alex",
                    "alex.jamal@gmail.com"
            );
            repository.saveAll(
                    List.of(mariam,alex)
            );
        };
    }
}
