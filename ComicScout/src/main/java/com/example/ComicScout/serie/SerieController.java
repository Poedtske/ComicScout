package com.example.ComicScout.serie;

import com.example.ComicScout.chapter.Chapter;
import com.example.ComicScout.chapter.ChapterService;
import com.example.ComicScout.user.User;
import com.example.ComicScout.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Series")
@CrossOrigin
public class SerieController {
    private final SerieService serieService;
    private ChapterService chapterService;

    @Autowired
    public void ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }
    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }



    @GetMapping
    public List<Serie>getSeries(){
        return serieService.getSeries();
    }

    @PostMapping
    public void registerNewSerie(@RequestBody Serie s){
        serieService.addNewSerie(s);
    }

    @DeleteMapping(path ="{serieId}")
    public void deleteSerie(@PathVariable("serieId") Long serieId){
        serieService.deleteSerie(serieId);
    }

/*@PutMapping(path ="{serieId}/Chapters/{chapterId}")
public Serie addChapterToSerie(
        @PathVariable Long serieId,
        @PathVariable Long chapterId


){
    Serie serie= serieService.getSerie(serieId);
    Chapter chapter= chapterService.getChapter(chapterId);
    serie.addChapter(chapter);
    return serieService.editSerie(serie);
}*/
}
