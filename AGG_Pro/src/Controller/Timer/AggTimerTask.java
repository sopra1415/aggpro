/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Timer;

import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author Heiko Geppert
 */
public class AggTimerTask extends TimerTask {

    @Override
    public void run() {
        AggTimer timer = AggTimer.getInstance();
        
        if (timer.getWork()){
            int temp;
            if ((timer.getHours()==0)&&(timer.getMinutes()==0)&&(timer.getSeconds()==0)){
                timer.setWork(false);
                JOptionPane.showMessageDialog(null, "Zeit ist um");
            }

            //decrement second
            temp = timer.getSeconds();
            if (temp>0){
                temp--;
                timer.setSeconds(temp);
            } else {
                timer.setSeconds(59);
                // decrement  minute
                temp = timer.getMinutes();
                if (temp>0){
                    temp--;
                    timer.setMinutes(temp);    
                } else {
                    // decrement hour
                    timer.setMinutes(59);
                    timer.setHours(timer.getHours()-1);
                }            
            }
            timer.observe();
        }
    }
    
}
