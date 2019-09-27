package com.alexey.service;

import com.alexey.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    Movie getById(Integer id);
    List<Movie> getMoviesByFilmmaker(Integer id);
    Integer addMovie(Movie movie);
    void updateMovie(Movie movie);
    void removeMovie(Integer id);
}
