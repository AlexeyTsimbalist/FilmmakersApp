package com.alexey.service;

import com.alexey.dao.MovieDao;
import com.alexey.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @Mock
    MovieDao movieDao;

    @InjectMocks
    MovieServiceImpl movieService;

    @Test
    public void getAllTest(){
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(1, "Green book", new Date(),
                120, 1, "First Last"));
        list.add(new Movie(2, "Red book", new Date(),
                122, 3, "First Last"));

        when(movieDao.getAll()).thenReturn(list);

        List<Movie> moviesFromDB = movieService.getAll();
        Assertions.assertTrue(list.size()==2);
        Assertions.assertEquals(list, moviesFromDB);
    }

    @Test
    public void getByIdTest(){
        Movie movie = new Movie(1, "Green book", new Date(),
                120, 1, "First Last");

        when(movieDao.getById(anyInt())).thenReturn(movie);

        Movie movieFromDB = movieService.getById(1);

        Assertions.assertEquals(movie, movieFromDB);
    }

    @Test
    public void getMoviesByFilmmakerTest(){
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(1, "Green book", new Date(),
                120, 1, "First Last"));
        list.add(new Movie(2, "Red book", new Date(),
                122, 3, "First Last"));

        when(movieDao.getMoviesByFilmmaker(anyInt())).thenReturn(list);

        List<Movie> moviesFromDB = movieService.getMoviesByFilmmaker(2);

        Assertions.assertEquals(list, moviesFromDB);
    }

    @Test
    public void addMovieTest(){
        Movie movie = new Movie(1, "Green book", new Date(),
                120, 1, "First Last");

        when(movieDao.insertMovie(any(Movie.class))).thenReturn(2);

        Integer movieId = movieService.addMovie(movie);

        Assertions.assertEquals(2, movieId);
    }

    @Test
    public void updateMovieTest(){
        Movie movie = new Movie(1, "Green book", new Date(),
                120, 1, "First Last");

        doNothing().when(movieDao).updateMovie(any(Movie.class));

        movieService.updateMovie(movie);

        verify(movieDao).updateMovie(movie);
    }

    @Test
    public void removeMovieTest(){
        doNothing().when(movieDao).deleteMovie(anyInt());

        movieService.removeMovie(2);

        verify(movieDao).deleteMovie(2);
    }
}
