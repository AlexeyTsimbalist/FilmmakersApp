package com.alexey.dao;

import com.alexey.model.Filmmaker;
import com.alexey.model.Movie;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface FilmmakerDao {
    List<Filmmaker> getAll() throws DataAccessException;
    Filmmaker getById(Integer id) throws DataAccessException;
    Integer insertFilmmaker(Filmmaker filmmaker) throws DataAccessException;
    void updateFilmmaker(Filmmaker filmmaker) throws DataAccessException;
    void deleteFilmmaker(Integer id) throws DataAccessException;
   // List<Movie> getHisMovies(Integer id) throws DataAccessException;
}
