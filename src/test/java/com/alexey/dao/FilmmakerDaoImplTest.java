package com.alexey.dao;


import com.alexey.config.TestConfig;
import com.alexey.model.Filmmaker;
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
public class FilmmakerDaoImplTest {

    @Autowired
    FilmmakerDao filmmakerDao;

    @Test
    public void getAllTest(){


        List<Filmmaker> filmmakers = filmmakerDao.getAll();

        Assertions.assertTrue(filmmakers.size()==5);


    }

    @Test
    void getByIdTest(){

        Filmmaker filmmaker = filmmakerDao.getById(1);
        Filmmaker filmmaker1 = filmmakerDao.getById(4);

        Assertions.assertEquals( "David", filmmaker.getFirstName());
        Assertions.assertEquals( "Andrey", filmmaker1.getFirstName());
    }

    @Test
    void insertFilmmakerTest(){
        Filmmaker filmmaker = new Filmmaker("Alexey", "Tsimbalist", "Belarus", new Date());
        Integer filmmakerId = filmmakerDao.insertFilmmaker(filmmaker);

        filmmaker = filmmakerDao.getById(filmmakerId);
        Assertions.assertEquals("Alexey", filmmaker.getFirstName());

    }

    @Test
    void updateFilmmakerTest(){
        Filmmaker filmmaker = filmmakerDao.getById(1);
        filmmaker.setFirstName("David (updated)");
        filmmaker.setCountry("Belarus");
        filmmakerDao.updateFilmmaker(filmmaker);

        filmmaker = filmmakerDao.getById(1);
        Assertions.assertEquals("David (updated)", filmmaker.getFirstName());
        Assertions.assertEquals("Belarus", filmmaker.getCountry());
    }

    @Test
    void deleteFilmmakerTest(){
        filmmakerDao.deleteFilmmaker(1);
        Assertions.assertThrows(EmptyResultDataAccessException.class, ()->filmmakerDao.getById(1));

    }
}
