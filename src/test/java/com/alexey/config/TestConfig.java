package com.alexey.config;

import com.alexey.dao.FilmmakerDao;
import com.alexey.dao.FilmmakerDaoImpl;
import com.alexey.dao.MovieDao;
import com.alexey.dao.MovieDaoImpl;
import com.alexey.service.FilmmakerService;
import com.alexey.service.FilmmakerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

@Configuration
@PropertySources({@PropertySource("classpath:sql.properties")})
public class TestConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create-tables.sql")
                .addScript("insert-values.sql")
                .build();

        return db;
    }

    @Bean
    public FilmmakerDao filmmakerDao(){
        return new FilmmakerDaoImpl(dataSource());
    }

    @Bean
    public MovieDao movieDao(){
        return new MovieDaoImpl(dataSource());
    }
}
