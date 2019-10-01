package com.alexey.controller;


import com.alexey.model.Movie;
import com.alexey.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void getAllTest() throws Exception {
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(1, "Green book", new Date(),
                120, 1, "First Last"));
        list.add(new Movie(2, "Red book", new Date(),
                122, 3, "First Last"));
        when(movieService.getAll()).thenReturn(list);

        mockMvc.perform(
                get("/movies")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(movieService).getAll();
    }

    @Test
    public void getMovieById() throws Exception {
        Movie movie = new Movie(1, "Green book", new Date(),
                120, 1, "First Last");

        when(movieService.getById(anyInt())).thenReturn(movie);

        mockMvc.perform(get("/movies/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(movieService).getById(2);
    }

    @Test
    public void getMoviesByFilmmaker() throws Exception {
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(1, "Green book", new Date(),
                120, 1, "First Last"));
        list.add(new Movie(2, "Red book", new Date(),
                122, 3, "First Last"));

        when(movieService.getMoviesByFilmmaker(anyInt())).thenReturn(list);

        mockMvc.perform(get("/movies/byfilmmaker/3")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(movieService).getMoviesByFilmmaker(3);
    }

    @Test
    public void createMovieTest() throws Exception {
        String movie = new ObjectMapper().writeValueAsString(new Movie(1, "Green book",
                new Date(),120, 1, "First Last"));

        when(movieService.addMovie(any(Movie.class))).thenReturn(2);

        mockMvc.perform(
                post("/movies")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(movie)
        ).andDo(print())
                .andExpect(status().isCreated());

        verify(movieService).addMovie(any(Movie.class));
    }

    @Test
    public void updateMovieTest() throws Exception {
        String movie = new ObjectMapper().writeValueAsString(new Movie(1, "Green book",
                new Date(),120, 1, "First Last"));

        doNothing().when(movieService).updateMovie(any(Movie.class));

        mockMvc.perform(
                put("/movies/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movie)
        ).andDo(print())
                .andExpect(status().isAccepted());

        verify(movieService).updateMovie(any(Movie.class));


    }

    @Test
    public void deleteMovieTest() throws Exception {
        doNothing().when(movieService).removeMovie(anyInt());

        mockMvc.perform(
                delete("/movies/2")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());

        verify(movieService).removeMovie(anyInt());
    }
}
