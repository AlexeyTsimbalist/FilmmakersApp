package com.alexey.service;

import com.alexey.dao.MovieDao;
import com.alexey.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao){
        this.movieDao=movieDao;
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie getById(Long id) {
        return movieDao.getById(id);
    }

    @Override
    public List<Movie> getMovieByFilmmaker(Long id) {
        return movieDao.getMoviesByFilmmaker(id);
    }

    @Override
    public Long addMovie(Movie movie) {
        return movieDao.insertMovie(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieDao.updateMovie(movie);
    }

    @Override
    public Long removeMovie(Long id) {
        return movieDao.deleteMovie(id);
    }
}
