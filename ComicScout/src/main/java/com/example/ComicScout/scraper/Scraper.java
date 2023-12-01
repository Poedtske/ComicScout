package com.example.ComicScout.scraper;


import com.example.ComicScout.chapter.Chapter;
import com.example.ComicScout.chapter.ChapterRepository;
import com.example.ComicScout.chapter.ChapterService;
import com.example.ComicScout.serie.Serie;
import com.example.ComicScout.serie.SerieRepository;
import com.example.ComicScout.serie.SerieService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin
public class Scraper {

    private String url;
    SerieService serieService;
    ChapterService chapterService;
    ChapterRepository cRepository;
    SerieRepository sRepository;

    public Scraper(String url, ChapterRepository cRepository, SerieRepository sRepository) {
        this.url = url;
        this.cRepository = cRepository;
        this.sRepository = sRepository;

        this.serieService=new SerieService(sRepository);
        this.chapterService=new ChapterService(cRepository);
    }

    public Scraper(String url) {
        this.url = url;
    }

    public void getSerie() throws IOException{

        String name;
        String description="";
        Set<String> chapterList=new HashSet<>();
        final Document document= Jsoup.connect(url).get();
        Elements r= document.select("div.listupd div.bs");

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

                    for (int y=1;y<=getNrOfChapters(ticker);y++){
                        chapterList.add(chapter+"chapter-"+y+"/");
                        System.out.println(chapter+"chapter-"+y+"/");
                        System.out.println(title);
                        System.out.println(ticker);
                        System.out.println(imgS);
                    }


                    name=title;
                    //System.out.println(chapter);
                    //System.out.println(title);
                    //System.out.println(ticker);
                    //System.out.println(imgS);

                }
            }
        }
    }
    public void getSerieService() throws IOException{

        String name;
        String description="";
        final Document document= Jsoup.connect(url).get();
        Elements r= document.select("div.listupd div.bs");

        for (int i=1;i<=r.stream().count();i++){
            for (Element row: document.select(
                    "div.listupd div")){
                if(row.select("div.bs:nth-of-type("+i+")").text().equals("")){
                    continue;
                }else{
                    List<Long> ids=new ArrayList<Long>();
                    final String ticker=row.select("div.bs:nth-of-type("+i+") div a").attr("href");
                    String title= ticker.replaceAll("https://flamecomics.com/series","");
                    title=title.replace("/","");
                    final String imgS= row.select("div.bs:nth-of-type("+i+") div a div.limit img").attr("src");
                    String chapter= "https://flamecomics.com/"+title+"-";

                    Serie s= new Serie(title,"",imgS);
                    serieService.addNewSerie(s);
                    for (int y=1;y<=getNrOfChapters(ticker);y++){
                        Chapter c=new Chapter("Chapter "+y,chapter+"chapter-"+y+"/");
                        c.addSerie(s);
                        chapterService.addNewChapter(c);
                        s.addChapter(c);
                        //System.out.println(chapter+"chapter-"+y+"/");
                    }



                    name=title;
                    //System.out.println(chapter);
                    //System.out.println(title);
                    //System.out.println(ticker);
                    //System.out.println(imgS);

                }
            }
        }
    }

    public void getSerieRepository() throws IOException{

        String name;
        String description="";
        final Document document= Jsoup.connect(url).get();
        Elements r= document.select("div.listupd div.bs");

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

                    Serie s= new Serie(title,"",imgS);

                    for (int y=1;y<=getNrOfChapters(ticker);y++){
                        Chapter c=new Chapter(s.getSerieName()+":Chapter"+y,chapter+"chapter-"+y+"/");
                        //c.addSerie(s);
                        cRepository.save(c);
                        s.addChapter(c);
                        //System.out.println(chapter+"chapter-"+y+"/");
                    }
                    sRepository.save(s);


                    name=title;
                    //System.out.println(chapter);
                    //System.out.println(title);
                    //System.out.println(ticker);
                    //System.out.println(imgS);

                }
            }
        }
    }

    public long getNrOfChapters(String url) throws IOException{
        final Document chaptersSerie=Jsoup.connect(url).get();
        Elements d= chaptersSerie.select("div.eplister ul li");
        return d.stream().count();
    }
}
