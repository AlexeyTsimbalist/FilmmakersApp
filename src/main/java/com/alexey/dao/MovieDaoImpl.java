package com.alexey.dao;

import com.alexey.model.Filmmaker;
import com.alexey.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${movie.findAll}")
    String findAllQuery;

    @Value("${movie.findById}")
    private String findByIdQuery;

    @Value("${movie.findAllByFilmmaker}")
    private String findMoviesByFilmmakerQuery;

    @Value("${movie.insert}")
    private String insertQuery;

    @Value("${movie.update}")
    private String updateQuery;

    @Value("${movie.delete}")
    private String deleteQuery;

    @Autowired
    public MovieDaoImpl(DataSource dataSource){
        this.namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Movie> movieRowMapper = ((resultSet, i) -> new Movie(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getDate("year"),
            resultSet.getInt("duration"),
            resultSet.getLong("filmmaker_id"),
            resultSet.getString("filmmaker_name")
    ));


    @Override
    public List<Movie> getAll() throws DataAccessException {
        return namedParameterJdbcTemplate.query(findAllQuery,movieRowMapper);
    }

    @Override
    public Movie getById(Long id) throws DataAccessException{
        return namedParameterJdbcTemplate.queryForObject(findByIdQuery,new MapSqlParameterSource("id",id),movieRowMapper);
    }

    @Override
    public List<Movie> getMoviesByFilmmaker(Long id) throws DataAccessException {
        return namedParameterJdbcTemplate.query(findMoviesByFilmmakerQuery, new MapSqlParameterSource("id",id), movieRowMapper);
    }

    @Override
    public Long insertMovie(Movie movie) throws DataAccessException{
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("name", movie.getName());
        namedParameter.addValue("year", movie.getYear());
        namedParameter.addValue("duration", movie.getDuration());
        namedParameter.addValue("filmmakerId", movie.getFilmmaker().getId());
        namedParameterJdbcTemplate.update(insertQuery, namedParameter, keyHolder, new String[] {"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateMovie(Movie movie) throws DataAccessException{
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("id", movie.getId());
        namedParameter.addValue("name", movie.getName());
        namedParameter.addValue("year", movie.getYear());
        namedParameter.addValue("duration", movie.getDuration());
        namedParameter.addValue("filmmakerId", movie.getFilmmaker().getId());
        namedParameterJdbcTemplate.update(updateQuery, namedParameter);
    }

    @Override
    public void deleteMovie(Long id) throws DataAccessException{
        namedParameterJdbcTemplate.update(deleteQuery, new MapSqlParameterSource("id",id));

    }
}
