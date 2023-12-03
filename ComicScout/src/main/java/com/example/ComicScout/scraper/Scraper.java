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

    private final String url;
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


    /*
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
    }*/
    public Boolean getSerieService() throws IOException{


        String description="";
        final Document document= Jsoup.connect(url).get();
        //Check if there is still a page and stops the while loop if there isn't
        if (document.select("div.listupd center.noresult").hasText()){
            System.out.println("Page nonexistent");
            return false;
        }
        else{
            Elements r= document.select("div.listupd div.bs");

            for (int i=1;i<=r.stream().count();i++){
                for (Element row: document.select(
                        "div.listupd div")){
                    if(row.select("div.bs:nth-of-type("+i+")").text().equals("")){
                        continue;
                    }else{
                        List<Long> ids=new ArrayList<Long>();
                        //ticker is to get to the page of the serie bv. https://flamecomics.com/series/forty-eight-hours-a-day/
                        final String ticker=row.select("div.bs:nth-of-type("+i+") div a").attr("href");
                        //Used to get the chapters bv.forty-eight-hours-a-day/
                        String titleUrl = ticker.replaceAll("https://flamecomics.com/series","");
                        //forty-eight-hours-a-day
                        titleUrl = titleUrl.replace("/","");
                        //get the title of the serie bv. 48 hours a Day
                        String titleSerie=row.select("div.bs:nth-of-type("+i+") div a div.bigor div.tt").text();


                        //to get the cover
                        final String imgS= row.select("div.bs:nth-of-type("+i+") div a div.limit img").attr("src");
                        //to get the first part of the html right for a chapter bv.https://flamecomics.com/forty-eight-hours-a-day-
                        String chapter= "https://flamecomics.com/"+ titleUrl +"-";



                        Serie s= new Serie(titleSerie,"",imgS,"");
                        serieService.addNewSerie(s);
                        addChaptersToSerie(ticker,s, chapter);
                    }
                }
            }
        }
        return true;
    }


    public void addChaptersToSerie(String url, Serie s, String p) throws IOException{
        final Document chaptersSerie=Jsoup.connect(url).get();
        Elements chapterAmount= chaptersSerie.select("div.eplister ul li");
        String allChapterNames= chaptersSerie.select("div.eplister ul li a div.chbox div.eph-num span.chapternum").text();
        String[] chapterNames=allChapterNames.split("Chapter ");
        for (String number:chapterNames
             ) {
            if(number=="") {
                continue;
            }
            number = number.replace(" ","");
            try {
                Chapter c = new Chapter("Chapter " + number, p + "chapter-" + number + "/");
                c.addSerie(s);
                chapterService.addNewChapter(c);
                s.addChapter(c);
            }catch (IllegalStateException e){
                continue;
            }

        }
    }


}
