package com.example.ComicScout.serie;

import com.example.ComicScout.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//add business functionalities
@Service
public class SerieService {

    private final SerieRepository serieRepository;

    @Autowired
    public SerieService(SerieRepository serieRepository){this.serieRepository = serieRepository;}

    public List<Serie>getSeries(){return serieRepository.findAll();}

    public Serie editSerie(Serie s) {
        //Optional<User> userOptional= serieRepository.findUserByEmail(u.getEmail());
        //if(userOptional.isPresent()){
        //    throw new IllegalStateException("email taken");
        //}
        return serieRepository.save(s);
    }

    public void addNewSerie (Serie s){
        serieRepository.save(s);
    }

    public Serie getSerie(Long serieId){
        return serieRepository.findSerieById(serieId);
    }

    public Serie editChapters(Serie s) {
        //Optional<User> userOptional= serieRepository.findUserByEmail(u.getEmail());
        //if(userOptional.isPresent()){
        //    throw new IllegalStateException("email taken");
        //}
        return serieRepository.save(s);
    }

    public void deleteSerie(Long serieId) {
        boolean exists= serieRepository.existsById(serieId);
        if(!exists){
            throw new IllegalStateException("serie with id "+serieId+" does not exist");
        }
        serieRepository.deleteById(serieId);
    }

    //entity goes in to managed state, this makes it so we don't need to do sql syntax
    /*@Transactional
    public void updateSerie(Long serieId, String name, String path) {
        Serie c = serieRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + "does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(c.getSerieName(), name)) {
            c.setSerieName(name);
        }


    }*/
}
