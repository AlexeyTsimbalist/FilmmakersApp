package com.alexey.dao;

import com.alexey.model.Filmmaker;
import com.alexey.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:sql.properties")
public class FilmmakerDaoImpl implements FilmmakerDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${filmmaker.findAll}")
    String findAllQuery;

    @Value("${filmmaker.findById}")
    private String findByIdQuery;

    @Value("${filmmaker.insert}")
    private String insertQuery;

    @Value("${filmmaker.update}")
    private String updateQuery;

    @Value("${filmmaker.delete}")
    private String deleteQuery;

    @Value("${filmmaker.getHisMovies}")
    private String hisMoviesQuery;

    @Autowired
    public FilmmakerDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Filmmaker> filmmakerRowMapper = ((resultSet, i) -> new Filmmaker(
            resultSet.getLong("id"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name"),
            resultSet.getString("country"),
            resultSet.getDate("date_of_birth")

    ));

    private RowMapper<Movie> movieRowMapper = ((resultSet, i) -> new Movie(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getDate("year"),
            resultSet.getInt("duration")
    ));

    @Override
    public List<Movie> getHisMovies(Long id) throws DataAccessException {
        return namedParameterJdbcTemplate.query(hisMoviesQuery, new MapSqlParameterSource("id", id), movieRowMapper);
    }

    @Override
    public List<Filmmaker> getAll() throws DataAccessException {
        return namedParameterJdbcTemplate.query(findAllQuery, filmmakerRowMapper);
    }

    @Override
    public Filmmaker getById(Long id) throws DataAccessException {
        return namedParameterJdbcTemplate.queryForObject(findByIdQuery, new MapSqlParameterSource("id", id), filmmakerRowMapper);
    }

    @Override
    public Long insertFilmmaker(Filmmaker filmmaker) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(filmmaker);
        namedParameterJdbcTemplate.update(insertQuery, namedParameter, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateFilmmaker(Filmmaker filmmaker) throws DataAccessException {
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(filmmaker);
        namedParameterJdbcTemplate.update(updateQuery, namedParameter);
    }

    @Override
    public Long deleteFilmmaker(Long id) throws DataAccessException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource namedParameter = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(deleteQuery, namedParameter, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }
}
