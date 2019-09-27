package com.alexey.controller;


import com.alexey.model.Movie;
import com.alexey.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin
public class MovieController {

    MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getAll(){
       return movieService.getAll();
    }

    @GetMapping("/movies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovieById(@PathVariable Integer id){
        return movieService.getById(id);
    }

    @GetMapping("/movies/byfilmmaker/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getMoviesByFilmmaker(@PathVariable Integer id){
        return movieService.getMoviesByFilmmaker(id);
    }

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createMovie(@RequestBody Movie movie, UriComponentsBuilder ucBuilder){
        Integer id = movieService.addMovie(movie);

        UriComponents uriComponent = ucBuilder.path("/movies/{id}").buildAndExpand(id);
        return ResponseEntity.created(uriComponent.toUri()).build();
    }

    @PutMapping("/movies/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateMovie(@PathVariable Integer id, @RequestBody Movie movie){
        movie.setId(id);
        movieService.updateMovie(movie);
    }

    @DeleteMapping("/movies/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@PathVariable Integer id){
        movieService.removeMovie(id);
    }


}
