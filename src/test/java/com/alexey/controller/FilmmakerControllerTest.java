package com.alexey.controller;


import com.alexey.model.Filmmaker;
import com.alexey.model.Movie;
import com.alexey.service.FilmmakerService;
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


public class FilmmakerControllerTest {

    @Mock
    private FilmmakerService filmmakerService;

    @InjectMocks
    private FilmmakerController filmmakerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmmakerController).build();
    }

    @Test
    public void getAllFilmmakersTest() throws Exception {

        List<Filmmaker> filmmakers = new ArrayList<>();
        filmmakers.add(new Filmmaker(1, "Alexey", "Tsimbalist",
                "Belarus", new Date(), 0));
        filmmakers.add(new Filmmaker(2, "David", "Lynch",
                "Belarus", new Date(), 0));
        filmmakers.add(new Filmmaker(3, "Quentin", "Tsimbalist",
                "USA", new Date(), 0));

        when(filmmakerService.getAll()).thenReturn(filmmakers);

        mockMvc.perform(
                get("/filmmakers")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(filmmakerService).getAll();
    }

    @Test
    public void getFilmmakerById() throws Exception {
        Filmmaker filmmaker = new Filmmaker(1, "Alexey", "Tsimbalist",
                "Belarus", new Date(), 0);

        when(filmmakerService.getById(anyInt())).thenReturn(filmmaker);

        mockMvc.perform(get("/filmmakers/2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(filmmakerService).getById(2);
    }

    @Test
    public void getFilmmakersMoviesTest() throws Exception {
        Filmmaker filmmaker = mock(Filmmaker.class);
        List<Movie> list = new ArrayList<>();
        list.add(new Movie(1, "Green book", new Date(),
                120, 1, "First Last"));
        list.add(new Movie(2, "Red book", new Date(),
                122, 3, "First Last"));
        filmmaker.setMovies(list);
        when(filmmakerService.getById(anyInt())).thenReturn(filmmaker);

         mockMvc.perform(get("/filmmakers/3/movies")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

         verify(filmmakerService.getById(anyInt())).getMovies();



    }

    @Test
    public void addFilmmakerTest() throws Exception{
        String filmmaker = new ObjectMapper().writeValueAsString(new Filmmaker(1, "Alexey", "Tsimbalist",
                "Belarus", new Date(), 0));

        when(filmmakerService.addFilmmaker(any(Filmmaker.class))).thenReturn(2);

        mockMvc.perform(
                post("/filmmakers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(filmmaker)
        ).andDo(print())
                .andExpect(status().isCreated());

        verify(filmmakerService).addFilmmaker(any(Filmmaker.class));
    }

    @Test
    public void updateFilmmakerTest() throws Exception{
        String filmmaker = new ObjectMapper().writeValueAsString(new Filmmaker(1, "Alexey", "Tsimbalist",
                "Belarus", new Date(), 0));

        doNothing().when(filmmakerService).updateFilmmaker(any(Filmmaker.class));

        mockMvc.perform(
                put("/filmmakers/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(filmmaker)
        ).andDo(print())
                .andExpect(status().isAccepted());

        verify(filmmakerService).updateFilmmaker(any(Filmmaker.class));
    }

    @Test
    public void deleteFilmmakerTest() throws Exception {
        doNothing().when(filmmakerService).removeFilmmaker(anyInt());

        mockMvc.perform(
                delete("/filmmakers/2")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());

        verify(filmmakerService).removeFilmmaker(2);
    }
}
