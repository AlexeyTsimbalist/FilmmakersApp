package com.alexey.service;

import com.alexey.model.Filmmaker;
import com.alexey.model.Movie;

import java.util.List;

public interface FilmmakerService {
    List<Filmmaker> getAll();
    Filmmaker getById(Integer id);
    Integer addFilmmaker(Filmmaker filmmaker);
    void updateFilmmaker(Filmmaker filmmaker);
    void removeFilmmaker(Integer id);
}
