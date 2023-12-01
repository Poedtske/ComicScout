package com.example.ComicScout.chapter;

import com.example.ComicScout.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//used to indicate that the class provides the mechanism for storage,
// retrieval, search, update and delete operation on objects
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @Query("SELECT c FROM Chapter c WHERE c.id= ?1")
    Chapter findChapterById(long id);

    @Query("SELECT c FROM Chapter c WHERE c.path= ?1")
    Optional<Chapter> findChapterByPath(String path);
}
