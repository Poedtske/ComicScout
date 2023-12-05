package com.example.ComicScout.serie;

import com.example.ComicScout.chapter.Chapter;
import com.example.ComicScout.chapter.ChapterService;
import com.example.ComicScout.user.User;
import com.example.ComicScout.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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



    @GetMapping(path = "/getAll")
    public List<Serie>getSeries(){
        return serieService.getSeries();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public void registerNewSerie(@RequestBody Serie s){
        serieService.addNewSerie(s);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path ="{serieId}")
    public void deleteSerie(@PathVariable("serieId") Long serieId){
        serieService.deleteSerie(serieId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path ="{serieId}")
    public void updateSerie(
            @PathVariable("serieId") Long serieId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String cover
    ){
        serieService.updateSerie(serieId,name,description,cover);
    }

}
