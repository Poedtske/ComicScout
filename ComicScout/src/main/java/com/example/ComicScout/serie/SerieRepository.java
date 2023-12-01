package com.example.ComicScout.serie;

import com.example.ComicScout.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//used to indicate that the class provides the mechanism for storage,
// retrieval, search, update and delete operation on objects
@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    @Query("SELECT s FROM Serie s WHERE s.id= ?1")
    Serie findSerieById(long id);

    @Query("SELECT s FROM Serie s WHERE s.name= ?1")
    Optional<Serie> findSerieByName(String name);
}
