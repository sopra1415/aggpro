/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.InputPanes;

import Data.LiveClasses.KoSystem;
import Data.LiveClasses.SwissSystem;

/**
 *
 * @author Heiko Geppert
 */
public class TournamentSystemHolder {

    private String name;
    private int value1;
    private int value2;    
    
    /**
     *
     * @param name the name of the TournamentSystem
     * @param value1 at swiss system: number  of players after  the cut
     *              at ko System: the number of players
     * @param value2 at swissSystem: number of rounds 
     *              at koSystem != means doubleKO
     */
    public TournamentSystemHolder(String name, int value1, int value2) {
        this.name=name;
        this.value1 = value1;
        this.value2 = value2;
    }

    
    public SwissSystem getSwissSystem(){
        return new SwissSystem(name, value1, value2);
    }
    
    public KoSystem getKoSystem(){
        return new KoSystem(name, value1, false);
    }
    
    public String getName(){
        return name;
    }
    
    public int getValue1(){
        return value1;
    }
    
    public int getValue2(){
        return value2;
    }
    
}
