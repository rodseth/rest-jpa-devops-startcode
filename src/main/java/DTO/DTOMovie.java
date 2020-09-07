/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Movie;

/**
 *
 * @author MariHaugen
 */
public class DTOMovie {
    
  private String title;
   private int released; 
   private String[] actors;

    public DTOMovie(Movie movie) {
        this.title = movie.getTitle();
        this.released =movie.getReleased();
        this.actors= movie.getActors();
        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleased() {
        return released;
    }

    public void setYear(int year) {
        this.released = released;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }
    
}