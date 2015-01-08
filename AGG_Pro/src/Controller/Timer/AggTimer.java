/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Timer;

import java.util.ArrayList;
import java.util.Timer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Heiko Geppert
 */
public class AggTimer extends Timer{
    
    private static AggTimer instance;
    
    private static ArrayList<JComponent> components;
    
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
        this.components = new ArrayList<JComponent>();
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
    
    public String getTime(){
        return (this.hours+":"+this.minutes+":"+this.seconds);
    }
    
    public void clearObservedComponents(){
        components.clear();
    }
    
    public void addObservedComponent(JComponent component){
        this.components.add(component);
    }
    
    public void observe(){
        for (JComponent component:components){       
            try{
                JTextField tf = (JTextField)component;
                tf.setText(this.getTime());
            } catch (Exception e) {
                try {
                    JLabel lb = (JLabel)component;
                    lb.setText(this.getTime());
                }catch (Exception ex){
                    System.err.println("Zeit konnte nicht auf Objekt angeezeigt werden, Objekt ist nicht von unterstützem Typ.");
                }
            }
        }
    }
    
    
}
