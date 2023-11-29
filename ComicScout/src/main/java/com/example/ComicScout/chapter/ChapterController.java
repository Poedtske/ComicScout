package com.example.ComicScout.chapter;

import com.example.ComicScout.serie.Serie;
import com.example.ComicScout.serie.SerieController;
import com.example.ComicScout.serie.SerieService;
import com.example.ComicScout.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Chapters")
@CrossOrigin
public class ChapterController {
    private final ChapterService chapterService;
    private SerieService serieService;

    @Autowired
    public void SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping
    public List<Chapter>getChapters(){
        return chapterService.getChapters();
    }

    @PostMapping
    public void registerNewChapter(@RequestBody Chapter c){
        chapterService.addNewChapter(c);
    }

    @DeleteMapping(path ="{chapterId}")
    public void deleteChapter(@PathVariable("chapterId") Long chapterId){
        chapterService.deleteChapter(chapterId);
    }

    @PutMapping(path ="{chapterId}/Series/{serieId}")
    public Chapter addChapterToSerie(
            @PathVariable Long chapterId,
            @PathVariable Long serieId


    ){
        Serie serie= serieService.getSerie(serieId);
        Chapter chapter= chapterService.getChapter(chapterId);
        chapter.addSerie(serie);
        return chapterService.editChapter(chapter);
    }
}
