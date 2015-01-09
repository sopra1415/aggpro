/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Database;

import Data.LiveClasses.Event;
import java.io.File;
import java.util.ArrayList;
import sun.org.mozilla.javascript.regexp.RegExpImpl;

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
        for (File fileArray1 : fileArray) {
            String filename = fileArray1.getName();
            if(filename.contains(".mv.db")){
                filename.replace(".mv.db", "");
                events.add(filename);
                
            }
        }
            
        
    
        return events;
    }
    
}
