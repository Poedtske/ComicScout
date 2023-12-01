package com.example.ComicScout.chapter;

import com.example.ComicScout.serie.Serie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    //defines PRIMARY KEY
    @SequenceGenerator(
            name = "chapter_sequence",
            sequenceName = "chapter_sequence",
            allocationSize = 1
    )
    //generates the key
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "chapter_sequence"
    )
    private Long id;
    private String name;
    private String path;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serie_id",referencedColumnName = "id")
    private Serie serie;

    public Serie getSerie() {
        return serie;
    }

    public Chapter(){}

    public Chapter(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public String getChapterName() {
        return name;
    }

    public void setChapterName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public void addSerie(Serie serie) {
        this.serie=serie;
    }
}
