package com.example.ComicScout.user;

import com.example.ComicScout.serie.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//used to indicate that the class provides the mechanism for storage,
// retrieval, search, update and delete operation on objects
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //SELECT * from User WHERE email= ?
    @Query("SELECT u FROM User u WHERE u.email= ?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id= ?1")
    User findUserById(long id);

    Optional<User> findByUsername(String userName);
}
