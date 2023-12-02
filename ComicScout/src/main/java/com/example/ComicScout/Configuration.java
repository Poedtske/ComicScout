package com.example.ComicScout;

import com.example.ComicScout.chapter.Chapter;
import com.example.ComicScout.chapter.ChapterRepository;
import com.example.ComicScout.scraper.Scraper;
import com.example.ComicScout.serie.Serie;
import com.example.ComicScout.serie.SerieRepository;
import com.example.ComicScout.user.User;
import com.example.ComicScout.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
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
                    "people die",
                    "https://i1.sndcdn.com/artworks-W8KXhQeXZrv2YSJO-ctOyHA-t500x500.jpg",
                    "dfg"
            );
            sRepository.saveAll(
                    List.of(GoT)
            );

            Scraper flameComics=new Scraper("https://flamecomics.com/series/?page=1",cRepository,sRepository);
            try{
                //tries to save on to the database!doesn't work!
                //flameComics.getSerieRepository();
                flameComics.getSerieService();

                //Just prints out all the chapters
                //flameComics.getSerie();
            }catch (Exception e){
                e.printStackTrace();
            }

        };
    }
}