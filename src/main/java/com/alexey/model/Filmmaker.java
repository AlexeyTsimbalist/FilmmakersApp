package com.alexey.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filmmaker {

    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Europe/Moscow")
    private Date dateOfBirth;
    private List<Movie> movies = new ArrayList<>();
    private int count;

    public Filmmaker() {}

    public Filmmaker(Long id, String firstName, String lastName, String country, Date dateOfBirth/*, List<Movie> movies*/){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.country=country;
        this.dateOfBirth=dateOfBirth;
        //this.movies=movies;
    }

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
