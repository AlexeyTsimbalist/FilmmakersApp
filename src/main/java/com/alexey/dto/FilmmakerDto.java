package com.alexey.dto;


public class FilmmakerDto {
    private Long id;
    private String name;

    //when we create from request
    public FilmmakerDto(Long id){
        this.id=id;
    }

    //when we get from DB
    public FilmmakerDto(Long id, String name){
        this.id=id;
        this.name =name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
