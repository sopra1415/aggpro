package Data.LiveClasses;

import Data.Database.DatabaseConnector;
import java.sql.SQLException;

public class SwissSystem extends TournamentSystem{
	private int numberOfPlayersAfterCut;
	private int numberOfRounds;
        private DatabaseConnector dc;

   /**
    * Constructor without Databaseconnection
    * @param name
    * @param numberOfPlayersAfterCut
    * @param numberOfRounds 
    */
	public SwissSystem(String name, int numberOfPlayersAfterCut,
			int numberOfRounds) {
		super(name);
		this.numberOfPlayersAfterCut = numberOfPlayersAfterCut;
		this.numberOfRounds = numberOfRounds;
	}
        
        /**
         * Constructor to write in the Database
         * @param dc
         * @param name
         * @param numberOfPlayersAfterCut
         * @param numberOfRounds 
         */
        public SwissSystem(DatabaseConnector dc, String name,int numberOfPlayersAfterCut,int numberOfRounds) throws SQLException{
            super(name);
            this.dc = dc;
            this.numberOfPlayersAfterCut = numberOfPlayersAfterCut;
            this.numberOfRounds = numberOfRounds;
            this.id =  dc.insert(String.format("INSERT INTO swissSystem(NumberOfPlayersAfterCut, NumberOfRounds) VALUES(%d, %d)", numberOfPlayersAfterCut, numberOfRounds));

            
        }
        
        
	// getters and setters
	public int getNumberOfPlayersAfterCut() {
		return numberOfPlayersAfterCut;
	}
	public void setNumberOfPlayersAfterCut(int numberOfPlayersAfterCut) {
		this.numberOfPlayersAfterCut = numberOfPlayersAfterCut;
	}
        
	public int getNumberOfRounds() {
		return numberOfRounds;
	}
	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}
        
        
        
	
	





}
