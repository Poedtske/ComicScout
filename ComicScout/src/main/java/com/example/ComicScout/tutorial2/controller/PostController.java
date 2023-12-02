package com.example.ComicScout.tutorial2.controller;

import com.example.ComicScout.tutorial2.model.Post;
import com.example.ComicScout.tutorial2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository repository;

    @Autowired
    public PostController(PostRepository repository){
        this.repository=repository;
    }

    @GetMapping
    public List<Post> findAll(){
        return repository.findAll();
    }
}
