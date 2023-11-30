package com.example.ComicScout.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class WebScraper {
    public static void main(String[] args) {
        final String url="https://flamecomics.com/series/?page=1";

        Scraper flameComics= new Scraper(url);
        try{
            flameComics.getSerie();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
