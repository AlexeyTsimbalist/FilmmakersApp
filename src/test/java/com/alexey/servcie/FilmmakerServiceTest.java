package com.alexey.servcie;

import com.alexey.config.TestConfig;
import com.alexey.dao.FilmmakerDao;
import com.alexey.model.Filmmaker;
import com.alexey.service.FilmmakerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class FilmmakerServiceTest {

    @Autowired
    public FilmmakerService filmmakerService;



    public static final FilmmakerDao filmmakerDao = mock(FilmmakerDao.class);
    public static List<Filmmaker> list = new ArrayList<>();

    @BeforeAll
    public static void setup(){

        list.add(new Filmmaker(
           1,
           "Alexey",
                "Tsimbalist",
                "USA",
                new Date(),
                0
        ));
        when(filmmakerDao.getAll()).thenReturn(list);

    }

    @Test
    public void filmmakerServiceShouldGetList(){
        filmmakerService.getAll();
        Assertions.assertEquals(list, list);
    }


}
