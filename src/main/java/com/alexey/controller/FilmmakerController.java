package com.alexey.controller;

import com.alexey.model.Filmmaker;
import com.alexey.model.Movie;
import com.alexey.service.FilmmakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin
public class FilmmakerController {
    private final FilmmakerService filmmakerService;

    @Autowired
    public FilmmakerController(FilmmakerService filmmakerService){
        this.filmmakerService=filmmakerService;
    }

    @GetMapping("/filmmakers")
    @ResponseStatus(HttpStatus.OK)
    public List<Filmmaker> getAllFilmmakers() {
        return filmmakerService.getAll();
    }

    @GetMapping("/filmmakers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Filmmaker getFilmmakerById(@PathVariable("id") Integer id){
        return filmmakerService.getById(id);
    }

    @GetMapping("/filmmakers/{id}/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getFilmmakersMovies(@PathVariable Integer id){
        return filmmakerService.getById(id).getMovies();
    }

    @PostMapping("/filmmakers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createFilmmaker(@RequestBody Filmmaker filmmaker, UriComponentsBuilder ucBuilder) {
        Integer id = filmmakerService.addFilmmaker(filmmaker);

        UriComponents uriComponent = ucBuilder.path("/filmmakers/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponent.toUri()).build();
    }

    @PutMapping("/filmmakers/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateFilmmaker(@PathVariable("id") Integer id,@RequestBody Filmmaker filmmaker) {
        filmmaker.setId(id);

        filmmakerService.updateFilmmaker(filmmaker);
    }

    @DeleteMapping("/filmmakers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFilmmaker(@PathVariable("id") Integer id) {

        filmmakerService.removeFilmmaker(id);
    }



}
