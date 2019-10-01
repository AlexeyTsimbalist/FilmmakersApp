package com.alexey.service;

import com.alexey.config.TestConfig;
import com.alexey.dao.FilmmakerDao;
import com.alexey.model.Filmmaker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmmakerServiceImplTest {

    @Mock
    public FilmmakerDao filmmakerDaoMock;

    @InjectMocks
    public FilmmakerServiceImpl filmmakerService;

    @Test
    public void getAllTest(){
        List<Filmmaker> filmmakers = new ArrayList<>();
        filmmakers.add(new Filmmaker(1, "Alexey", "Tsimbalist",
                "Belarus", new Date(), 0));
        filmmakers.add(new Filmmaker(2, "David", "Lynch",
                "Belarus", new Date(), 0));
        filmmakers.add(new Filmmaker(3, "Quentin", "Tsimbalist",
                "USA", new Date(), 0));

        when(filmmakerDaoMock.getAll()).thenReturn(filmmakers);

        List<Filmmaker> filmmakersFromDB = filmmakerService.getAll();
        Assertions.assertTrue(filmmakersFromDB.size()==3);
        Assertions.assertEquals("Lynch", filmmakersFromDB.get(1).getLastName());

    }

    @Test
    public void getByIdTest(){
        Filmmaker filmmaker = new Filmmaker(1, "Alexey", "Tsimbalist",
                                    "Belarus", new Date(), 0);

        when(filmmakerDaoMock.getById(anyInt())).thenReturn(filmmaker);

        Filmmaker filmmakerFromDB = filmmakerService.getById(1);
        Assertions.assertEquals(filmmaker, filmmakerFromDB);
    }

    @Test
    public void addFilmmakerTest(){
        Filmmaker filmmaker = new Filmmaker(1, "Alexey", "Tsimbalist",
                "Belarus", new Date(), 0);

        when(filmmakerDaoMock.insertFilmmaker(filmmaker)).thenReturn(2);

        Integer filmmakerId = filmmakerService.addFilmmaker(filmmaker);
        Assertions.assertEquals(2, filmmakerId);
    }

    @Test
    public void updateFilmmakerTest(){
        Filmmaker filmmaker = new Filmmaker(1, "Alexey", "Tsimbalist",
                "Belarus", new Date(), 0);

        doNothing().when(filmmakerDaoMock).updateFilmmaker(filmmaker);

        filmmakerService.updateFilmmaker(filmmaker);

        verify(filmmakerDaoMock).updateFilmmaker(filmmaker);
    }

    @Test
    public void removeFilmmakerTest(){
        doNothing().when(filmmakerDaoMock).deleteFilmmaker(anyInt());

        filmmakerService.removeFilmmaker(1);

        verify(filmmakerDaoMock).deleteFilmmaker(1);
    }
}
