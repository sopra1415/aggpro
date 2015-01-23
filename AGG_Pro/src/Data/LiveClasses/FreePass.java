/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.LiveClasses;

/**
 *
 * @author Heiko Geppert
 */
public class FreePass extends Participant {

    private final boolean superFreepass;
    private int primaryScore;
    private int secondaryScore;
    private int numberOfRounds;

    /**
     *
     * @param superFreepass
     * @param r
     */
    public FreePass(boolean superFreepass, Round r) {
        this.superFreepass = superFreepass;
        try {
            SwissSystem actualSystem = ((SwissSystem) r.getTournament().getModul().getTouramentSystem(r.getRound()));
            numberOfRounds = actualSystem.getNumberOfRounds();
            
            if (this.superFreepass) {
                primaryScore = numberOfRounds * r.getTournament().getModul().getPointsWin();
                secondaryScore = numberOfRounds * r.getTournament().getModul().getPointsWin();
            } else {
                primaryScore = numberOfRounds * r.getTournament().getModul().getPointsDraw();
                secondaryScore = numberOfRounds * r.getTournament().getModul().getPointsDraw();
            }

        } catch (ClassCastException e) {
            System.err.println("falsche implementierung der freePasses");
            e.printStackTrace();
        }
    }
    
    public int getPrimaryScore(){
        return primaryScore;
    }
    
    public int getSecondaryScore(){
        return secondaryScore;
    }
    
    @Override
    public String getName(){
        if (superFreepass){
            return ("Superfreilos");
        } else {
            return ("Freilos");
        }
    }
    
    @Override
    public String getNickname(){
        if (superFreepass){
            return ("Superfreilos");
        } else {
            return ("Freilos");
        }
    }
    
    @Override
    public String getPrename(){
        if (superFreepass){
            return ("Superfreilos");
        } else {
            return ("Freilos");
        }
    }
    
    @Override
    public String getStartnumber(){
        if (superFreepass){
            return ("Superfreilos");
        } else {
            return ("Freilos");
        }
    }
}
