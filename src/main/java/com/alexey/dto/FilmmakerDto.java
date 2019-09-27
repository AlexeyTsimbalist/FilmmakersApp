package com.alexey.dto;


public class FilmmakerDto {
    private Integer id;
    private String name;

    public FilmmakerDto(){}
    //when we create from request
    public FilmmakerDto(Integer id){
        this.id=id;
    }

    //when we get from DB
    public FilmmakerDto(Integer id, String name){
        this.id=id;
        this.name =name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
