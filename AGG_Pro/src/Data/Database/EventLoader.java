/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author tobias
 */
public class EventLoader {
    private ArrayList<String> events;
    public EventLoader(){
        events = new ArrayList<String>();
    }
    public ArrayList<String> getEvents(){
        
        File f = new File(System.getProperty("user.home")+"/aggpro/");
        File[] fileArray = f.listFiles();
        try {
            for (File file : fileArray) {
                String filename = file.getName();
                if(filename.contains(".mv.db")){
                    filename.replace(".mv.db", "");
                    events.add(filename);

                }
            }    
        } catch(NullPointerException e){}        
    
        return events;
    }
    
}
