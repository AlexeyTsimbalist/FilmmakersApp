package com.alexey.dao;

import com.alexey.model.Movie;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll() throws DataAccessException;
    Movie getById(Long id) throws DataAccessException;
    List<Movie> getMoviesByFilmmaker(Long id) throws DataAccessException;
    Long insertMovie(Movie movie) throws DataAccessException;
    void updateMovie(Movie movie) throws DataAccessException;
    void deleteMovie(Long id) throws DataAccessException;
}
