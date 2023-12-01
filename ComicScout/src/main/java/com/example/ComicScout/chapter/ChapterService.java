package com.example.ComicScout.chapter;

import com.example.ComicScout.serie.Serie;
import com.example.ComicScout.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//add business functionalities
@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;

    @Autowired
    public ChapterService(ChapterRepository chapterRepository){this.chapterRepository = chapterRepository;}

    public List<Chapter>getChapters(){return chapterRepository.findAll();}

    public void addNewChapter(Chapter c) {
        Optional<Chapter> chapterOptional= chapterRepository.findChapterByPath(c.getPath());
        if(chapterOptional.isPresent()){
            throw new IllegalStateException("chapter is already present");
        }
        chapterRepository.save(c);
    }

    public void deleteChapter(Long chapterId) {
        boolean exists= chapterRepository.existsById(chapterId);
        if(!exists){
            throw new IllegalStateException("chapter with id "+chapterId+" does not exist");
        }
        chapterRepository.deleteById(chapterId);
    }

    public Chapter getChapter(Long chapterId){
        return chapterRepository.findChapterById(chapterId);
    }

    public Chapter editChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

}
