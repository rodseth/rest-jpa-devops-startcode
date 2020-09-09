/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entities.Movie;
import java.util.StringJoiner;

/**
 *
 * @author MariHaugen
 */
public class MovieDTO {
   
   private long id;
   private int released;
   private String title; 
   private String actors;

    public MovieDTO(Movie m) {
        this.id = m.getId();
        this.released = m.getReleased();
        this.title = m.getTitle();
        StringJoiner joiner = new StringJoiner(", ");
        for(int i = 0; i < m.getActors().length; i++) {
         joiner.add(m.getActors()[i]);
      }
      this.actors = joiner.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
    
}