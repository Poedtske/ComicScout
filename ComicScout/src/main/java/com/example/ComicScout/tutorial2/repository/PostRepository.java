package com.example.ComicScout.tutorial2.repository;

import com.example.ComicScout.tutorial2.model.Post;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository extends ListCrudRepository<Post, Integer> {
}
