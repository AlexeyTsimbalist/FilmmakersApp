package com.alexey.service;

import com.alexey.model.Filmmaker;
import com.alexey.model.Movie;

import java.util.List;

public interface FilmmakerService {
    List<Filmmaker> getAll();
    Filmmaker getById(Long id);
    Long addFilmmaker(Filmmaker filmmaker);
    void updateFilmmaker(Filmmaker filmmaker);
    void removeFilmmaker(Long id);
}
