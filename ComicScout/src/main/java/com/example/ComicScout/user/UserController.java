package com.example.ComicScout.user;

import com.example.ComicScout.serie.Serie;
import com.example.ComicScout.serie.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    private SerieService serieService;

    @Autowired
    public void SerieController(SerieService serieService) {
        this.serieService = serieService;
    }
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/getAll")
    public List<User>getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User u){
        userService.addNewUser(u);
    }

    @DeleteMapping(path ="{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping(path ="{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        userService.updateUser(userId,name,email);
    }

    @PutMapping(path ="{userId}/Series/{serieId}")
    public User addBookmark(
            @PathVariable Long userId,
            @PathVariable Long serieId

    ){
        Serie serie= serieService.getSerie(serieId);
        User user= userService.getUser(userId);
        user.addBookmark(serie);
        return userService.editBookmarks(user);
    }
}
