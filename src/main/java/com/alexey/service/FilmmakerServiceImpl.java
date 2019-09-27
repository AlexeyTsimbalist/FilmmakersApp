package com.alexey.service;

import com.alexey.dao.FilmmakerDao;
import com.alexey.model.Filmmaker;
import com.alexey.model.Movie;
import com.alexey.service.exception.OperationFailedException;
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

    public void checkFilmmaker(Filmmaker filmmaker){
        if (filmmaker == null)
            throw new OperationFailedException("Filmmaker should not be null");
        if (filmmaker.getFirstName()==null)
            throw new OperationFailedException("Filmmaker's first name should not be null");
        if (filmmaker.getLastName()==null)
            throw new OperationFailedException("Filmmaker's last name should not be null");
        if (filmmaker.getCountry()==null)
            throw new OperationFailedException("Filmmakers's country should not be null");
        if (filmmaker.getDateOfBirth()==null)
            throw new OperationFailedException("Filmmaker's date of birth should not be null");
    }

    @Override
    public List<Filmmaker> getAll() {
        return filmmakerDao.getAll();
    }

    @Override
    public Filmmaker getById(Long id) {
        if(id == null || id <=0)
            throw new OperationFailedException("Filmmaker's id should be more than 0 or not a null");
        return filmmakerDao.getById(id);
    }

    @Override
    public Long addFilmmaker(Filmmaker filmmaker) {
        checkFilmmaker(filmmaker);
        return filmmakerDao.insertFilmmaker(filmmaker);
    }

    @Override
    public void updateFilmmaker(Filmmaker filmmaker) {
        if (filmmaker.getId()==null || filmmaker.getId()<=0)
            throw new OperationFailedException("Filmmaker's id should be more than 0 or not a null");
        checkFilmmaker(filmmaker);
        filmmakerDao.updateFilmmaker(filmmaker);

    }

    @Override
    public void removeFilmmaker(Long id) {
        if (id == null || id<=0)
            throw new OperationFailedException("Filmmaker's id should be more than 0 or not a null");
        filmmakerDao.deleteFilmmaker(id);
    }

}
