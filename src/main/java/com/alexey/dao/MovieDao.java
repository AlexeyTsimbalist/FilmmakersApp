package com.alexey.dao;

import com.alexey.model.Movie;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MovieDao {
    List<Movie> getAll() throws DataAccessException;
    Movie getById(Integer id) throws DataAccessException;
    List<Movie> getMoviesByFilmmaker(Integer id) throws DataAccessException;
    Integer insertMovie(Movie movie) throws DataAccessException;
    void updateMovie(Movie movie) throws DataAccessException;
    void deleteMovie(Integer id) throws DataAccessException;
}
