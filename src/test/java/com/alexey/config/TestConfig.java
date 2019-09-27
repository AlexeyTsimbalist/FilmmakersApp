package com.alexey.config;

import com.alexey.dao.FilmmakerDao;
import com.alexey.service.FilmmakerService;
import com.alexey.service.FilmmakerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration

public class TestConfig {

    @Bean
    public FilmmakerDao filmmakerDao(){
        return mock(FilmmakerDao.class);
    }

    @Bean
    public FilmmakerService filmmakerService(){
        return new FilmmakerServiceImpl(filmmakerDao());
    }
}
