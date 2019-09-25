package com.alexey.model;

import com.alexey.dto.FilmmakerDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Movie {
    private Long id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy", timezone="Europe/Moscow")
    private Date year;
    private Integer duration;
    private FilmmakerDto filmmaker;

    //When we get from DB
    public Movie(Long id, String name, Date year, Integer duration,Long filmmakerId, String filmmaker){
        this.id=id;
        this.name=name;
        this.year=year;
        this.duration=duration;
        this.filmmaker=new FilmmakerDto(filmmakerId, filmmaker);
    }

    //When we create from request
    public Movie(String name, Date year, Integer duration, Long filmmakerId){
        this.name=name;
        this.year=year;
        this.duration=duration;
        new FilmmakerDto(filmmakerId);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public FilmmakerDto getFilmmaker() {
        return filmmaker;
    }

    public void setFilmmaker(FilmmakerDto filmmaker) {
        this.filmmaker = filmmaker;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                '}';
    }
}
