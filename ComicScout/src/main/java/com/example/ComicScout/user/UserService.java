package com.example.ComicScout.user;

import com.example.ComicScout.serie.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

//add business functionalities
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository=userRepository;}

    public List<User>getUsers(){return userRepository.findAll();}

    public void addNewUser(User u) {
        Optional<User> userOptional= userRepository.findUserByEmail(u.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userRepository.save(u);
    }

    public User editBookmarks(User u) {
        //Optional<User> userOptional= serieRepository.findUserByEmail(u.getEmail());
        //if(userOptional.isPresent()){
        //    throw new IllegalStateException("email taken");
        //}
        return userRepository.save(u);
    }

    public void deleteUser(Long userId) {
        boolean exists=userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("user with id "+userId+" does not exist");
        }
        userRepository.deleteById(userId);
    }

    //entity goes in to managed state, this makes it so we don't need to do sql syntax
    @Transactional
    public void updateUser(Long userId, String name, String email) {
        User u = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + "does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(u.getUserName(), name)) {
            u.setUserName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(u.getEmail(), email)) {
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            u.setEmail(email);
        }
    }

    public User getUser(Long userId){
        return userRepository.findUserById(userId);
    }
}
