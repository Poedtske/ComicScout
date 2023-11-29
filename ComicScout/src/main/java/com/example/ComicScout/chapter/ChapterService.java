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
        //Optional<User> userOptional= chapterRepository.findUserByEmail(u.getEmail());
        //if(userOptional.isPresent()){
        //    throw new IllegalStateException("email taken");
        //}
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

    //entity goes in to managed state, this makes it so we don't need to do sql syntax
    /*@Transactional
    public void updateChapter(Long chapterId, String name, String path) {
        Chapter c = chapterRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + "does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(c.getChapterName(), name)) {
            c.setChapterName(name);
        }

        if (path != null && path.length() > 0 && !Objects.equals(c.getPath(), path)) {
            //Optional<Chapter> chapterOptional = chapterRepository.findCapterByEmail(email);
            if (chapterOptional.isPresent()) {
                throw new IllegalStateException("chapter taken");
            }
            c.setEmail(email);
        }
    }*/
}
