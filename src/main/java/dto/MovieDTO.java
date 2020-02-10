/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author oscar
 */
public class MovieDTO {
    
    public int movieID;
    public int year;
    public String name;
    
    public MovieDTO (Movie movie){
        int id = movie.getId().intValue();
        this.movieID=id;
        this.year= movie.getYear();
        this.name=movie.getName();
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
