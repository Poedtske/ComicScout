package com.example.ComicScout;

import com.example.ComicScout.chapter.Chapter;
import com.example.ComicScout.chapter.ChapterRepository;
import com.example.ComicScout.serie.Serie;
import com.example.ComicScout.serie.SerieRepository;
import com.example.ComicScout.user.User;
import com.example.ComicScout.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository uRepository, ChapterRepository cRepository, SerieRepository sRepository){
        return args ->{
            User mariam= new User(
                    "Mariam",
                    "mariam.jamal@gmail.com"
            );
            User alex= new User(
                    "Alex",
                    "alex.jamal@gmail.com"
            );
            uRepository.saveAll(
                    List.of(mariam,alex)
            );
            Serie GoT= new Serie(
                    "Game of Thrones",
                    "funy"
            );
            sRepository.saveAll(
                    List.of(GoT)
            );
            Chapter one= new Chapter(
                    "1",
                    "https://www.kfdemoedigevrienden.be/"
            );
            cRepository.saveAll(
                    List.of(one)
            );
        };
    }
}
