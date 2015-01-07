/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Timer;

/**
 *
 * @author Heiko Geppert
 */
public class AggTimer extends Timer{
    
    private static AggTimer instance;
    
    private int hours;
    private int minutes;
    private int seconds;
    
    public static AggTimer getInstance(){
        if (instance == null){
            instance = new AggTimer();
        }     
        return instance;
    }
    
    private AggTimer(){
        super();
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }
    
    public int getHours(){
        return this.hours;
    }
    
    public void setHours(int i){
        this.hours = i;
    }
    
    public int getMinutes(){
        return this.minutes;
    }
    
    public void setMinutes(int i){
        this.minutes = i;
    }
    
    public int getSeconds(){
        return this.seconds;
    }
    
    public void setSeconds(int i){
        this.seconds = i;
    }
}
