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
		final String url="https://flamecomics.com/series/?page=1";

		try{
			final Document document= Jsoup.connect(url).get();
			Elements r= document.select("div.listupd div.bs");

			System.out.println();
			for (int i=1;i<=r.stream().count();i++){
				for (Element row: document.select(
						"div.listupd div")){
					if(row.select("div.bs:nth-of-type("+i+")").text().equals("")){
						continue;
					}else{
						final String ticker=row.select("div.bs:nth-of-type("+i+") div a").attr("href");
						String title= ticker.replaceAll("https://flamecomics.com/series","");
						title=title.replace("/","");
						final String imgS= row.select("div.bs:nth-of-type("+i+") div a div.limit img").attr("src");
						String chapter= "https://flamecomics.com/"+title+"-";



						System.out.println(chapter);
						//System.out.println(title);
						//System.out.println(ticker);
						//System.out.println(imgS);

					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
