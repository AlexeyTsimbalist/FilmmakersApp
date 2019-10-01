package com.alexey.model;

import com.alexey.dto.FilmmakerDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Movie {
    private Integer id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Europe/Moscow")
    private Date releaseDate;
    private Integer duration;
    private FilmmakerDto filmmaker;

    public Movie(){

    };
    //When we get from DB
    public Movie(Integer id, String name, Date releaseDate, Integer duration, Integer filmmakerId, String filmmaker){
        this.id=id;
        this.name=name;
        this.releaseDate = releaseDate;
        this.duration=duration;
        this.filmmaker=new FilmmakerDto(filmmakerId, filmmaker);
    }

    public Movie(String name, Date releaseDate, Integer duration, Integer filmmakerId){
        this.name=name;
        this.releaseDate = releaseDate;
        this.duration=duration;
        this.filmmaker = new FilmmakerDto(filmmakerId);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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
                ", year=" + releaseDate +
                ", duration=" + duration +
                ", filmmakerId=" + filmmaker.getId() +
                '}';
    }
}
