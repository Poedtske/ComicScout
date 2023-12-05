package com.example.ComicScout.user;


import com.example.ComicScout.serie.Serie;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    //will uniquely identify the entity in the database
    @Id
    //defines PRIMARY KEY
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    //generates the key
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String username;
    private String email;
    private String password;
    private String roles;

    @ManyToMany()
    @JoinTable(
            name = "bookmarks",
            joinColumns =@JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "serie_id")
    )
    private Set<Serie> bookmarks= new HashSet<>();

    public User(){}

    public User(String username, String email, String password, String roles) {
        this.username = username;
        this.email = email;
        this.password=password;
        this.roles=roles;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password=password;
        roles="ROLE_USER";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Serie> getBookmarks() {
        return bookmarks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String role) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + roles +
                ", bookmarks=" + bookmarks +
                '}';
    }

    public void addBookmark(Serie s) {
        bookmarks.add(s);
    }

}
