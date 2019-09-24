package com.alexey.service;

import com.alexey.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    Movie getById(Long id);
    List<Movie> getMovieByFilmmaker(Long id);
    Long addMovie(Movie movie);
    void updateMovie(Movie movie);
    Long removeMovie(Long id);
}
