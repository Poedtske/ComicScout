package com.example.ComicScout;

import com.example.ComicScout.user.UserController;
import com.example.ComicScout.user.UserRepository;
import com.example.ComicScout.user.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@RestController
public class ComicScoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicScoutApplication.class, args);
	}


}
