package com.alexey.service;

import com.alexey.dao.MovieDao;
import com.alexey.model.Movie;
import com.alexey.service.exception.OperationFailedException;
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

    public void checkMovie(Movie movie){
        if (movie.getName()==null)
            throw new OperationFailedException("Movie's name should not be null");
        if (movie.getReleaseDate()==null)
            throw new OperationFailedException("Movie's release date should not be null");
        if (movie.getDuration()==null) {
            throw new OperationFailedException("Movie's duration should not be null");
        }
        if (movie.getFilmmaker().getId()==null)
            throw new OperationFailedException("Movie's filmmaker's id should not be null");
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie getById(Integer id) {
        if(id == null || id <= 0)
            throw new OperationFailedException("Movie's id should be more than 0 or not a null");
        return movieDao.getById(id);
    }

    @Override
    public List<Movie> getMoviesByFilmmaker(Integer id) {
        if(id == null || id <= 0)
            throw new OperationFailedException("Filmmaker's id should be more than 0 or not a null");
        return movieDao.getMoviesByFilmmaker(id);
    }

    @Override
    public Integer addMovie(Movie movie) {
        checkMovie(movie);
        return movieDao.insertMovie(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        checkMovie(movie);
        movieDao.updateMovie(movie);
    }

    @Override
    public void removeMovie(Integer id) {
        if (id == null || id <=0)
            throw new OperationFailedException("Movie's id should be more than 0 or not a null");
        movieDao.deleteMovie(id);
    }
}
