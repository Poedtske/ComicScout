package com.example.ComicScout.serie;

import com.example.ComicScout.chapter.Chapter;
import com.example.ComicScout.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "series")
public class Serie {
    @Id
    //defines PRIMARY KEY
    @SequenceGenerator(
            name = "serie_sequence",
            sequenceName = "serie_sequence",
            allocationSize = 1
    )
    //generates the key
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "_sequence"
    )
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "bookmarks")
    private Set<User> userList=new HashSet<>();


    @OneToMany(mappedBy = "serie")
    private Set<Chapter> chapters=new HashSet<>();

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public Serie(){}

    public Serie(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void addChapter(Chapter c) {
        chapters.add(c);
    }
}
