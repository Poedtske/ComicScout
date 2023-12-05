package com.example.ComicScout.chapter;

import com.example.ComicScout.serie.Serie;
import com.example.ComicScout.serie.SerieController;
import com.example.ComicScout.serie.SerieService;
import com.example.ComicScout.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(path = "/getAll")
    public List<Chapter>getChapters(){
        return chapterService.getChapters();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void registerNewChapter(@RequestBody Chapter c){
        chapterService.addNewChapter(c);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path ="{chapterId}")
    public void deleteChapter(@PathVariable("chapterId") Long chapterId){
        chapterService.deleteChapter(chapterId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path ="{chapterId}")
    public void updateChapter(
            @PathVariable("chapterId") Long chapterId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String path
    ){
        chapterService.updateChapter(chapterId,name,path);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path ="{chapterId}/Series/{serieId}")
    public Chapter addChapterToSerie(
            @PathVariable Long chapterId,
            @PathVariable Long serieId


    ){
        Serie serie= serieService.getSerie(serieId);
        Chapter chapter= chapterService.getChapter(chapterId);
        //chapter.addSerie(serie);
        return chapterService.editChapter(chapter);
    }
}
