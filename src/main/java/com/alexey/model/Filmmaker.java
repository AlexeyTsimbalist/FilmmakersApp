package com.alexey.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Filmmaker {

    private Integer id;
    private String firstName;
    private String lastName;
    private String country;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Europe/Moscow")
    private Date dateOfBirth;
    private Integer countOfMovies;
    private List<Movie> movies;

    public Filmmaker() {

    }


    //for getById()
    public Filmmaker(Integer id, String firstName, String lastName, String country, Date dateOfBirth,Integer countOfMovies, List<Movie> movies){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.country=country;
        this.dateOfBirth=dateOfBirth;
        this.countOfMovies=countOfMovies;
        this.movies=movies;
    }
    //for getAll()
    public Filmmaker(Integer id, String firstName, String lastName, String country, Date dateOfBirth, Integer countOfMovies){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.country=country;
        this.dateOfBirth=dateOfBirth;
        this.countOfMovies=countOfMovies;

    }
    //for insert()
    public Filmmaker(String firstName, String lastName, String country, Date dateOfBirth){
        this.firstName=firstName;
        this.lastName=lastName;
        this.country=country;
        this.dateOfBirth=dateOfBirth;
    }

    public Filmmaker(String firstName, String lastName, String country){
        this.firstName=firstName;
        this.lastName=lastName;
        this.country=country;
        this.dateOfBirth= new Date();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Integer getCountOfMovies() {
        return countOfMovies;
    }

    public void setCountOfMovies(Integer countOfMovies) {
        this.countOfMovies = countOfMovies;
    }

    @Override
    public String toString() {
        return "Filmmaker{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", countOfMovies=" + movies.size() +
                '}';
    }
}
