package com.alexey.service;

import com.alexey.dao.FilmmakerDao;
import com.alexey.model.Filmmaker;
import com.alexey.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmmakerServiceImpl implements FilmmakerService{

    FilmmakerDao filmmakerDao;

    @Autowired
    public FilmmakerServiceImpl(FilmmakerDao filmmakerDao){
        this.filmmakerDao=filmmakerDao;
    }

    @Override
    public List<Filmmaker> getAll() {
        List<Filmmaker> filmmakerList = filmmakerDao.getAll();
        return filmmakerList;
    }

    @Override
    public Filmmaker getById(Long id) {
        Filmmaker filmmaker = filmmakerDao.getById(id);
        return filmmaker;
    }

    @Override
    public Long addFilmmaker(Filmmaker filmmaker) {
        return filmmakerDao.insertFilmmaker(filmmaker);
    }

    @Override
    public void updateFilmmaker(Filmmaker filmmaker) {
        filmmakerDao.updateFilmmaker(filmmaker);

    }

    @Override
    public Long removeFilmmaker(Long id) {
        return filmmakerDao.deleteFilmmaker(id);
    }

    /*@Override
    public List<Movie> getHisMovies(Long id) {
        return filmmakerDao.getHisMovies(id);
    }*/
}
