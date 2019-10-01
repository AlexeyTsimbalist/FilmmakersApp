package com.alexey.dao;

import com.alexey.config.TestConfig;
import com.alexey.dto.FilmmakerDto;
import com.alexey.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class MovieDaoImplTest {

    @Autowired
    MovieDao movieDao;

    @Test
    public void getAllTest(){
        List<Movie> list = movieDao.getAll();

        Assertions.assertTrue(list.size()==7);
    }

    @Test
    public void getByIdTest(){
        Movie movie = movieDao.getById(3);

        Assertions.assertEquals("Lost Highway", movie.getName());
    }

    @Test
    public void getMoviesByFilmmakerTest(){
        List<Movie> list = movieDao.getMoviesByFilmmaker(3);

        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void insertMovieTest(){
        Movie movie = new Movie("Green book", new Date(),
                120, 1);

        Integer movieId = movieDao.insertMovie(movie);
        Movie movieFromDB = movieDao.getById(movieId);

        Assertions.assertEquals("Green book", movieFromDB.getName());
    }

    @Test
    public void updateMovieTest(){
        Movie movie = movieDao.getById(1);
        movie.setName("Update name");
        movie.setDuration(220);
        movieDao.updateMovie(movie);

        movie = movieDao.getById(1);

        Assertions.assertEquals("Update name", movie.getName());
        Assertions.assertEquals(220, movie.getDuration());
    }

    @Test
    public void deleteMovieTest(){
        movieDao.deleteMovie(1);
        Assertions.assertThrows(EmptyResultDataAccessException.class, ()->movieDao.getById(1));
    }
}
