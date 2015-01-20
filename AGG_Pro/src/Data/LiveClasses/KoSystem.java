package Data.LiveClasses;

import Data.Database.DatabaseConnector;
import java.sql.SQLException;

public class KoSystem extends TournamentSystem{
	private int numberOfPlayers;
	private boolean doubleKO;
        private DatabaseConnector dc;
        /**
         * Constructor without databaseconnection
         * @param name
         * @param numberOfPlayers
         * @param doubleKO 
         */
	public KoSystem(String name, int numberOfPlayers, boolean doubleKO) {
		super(name);
		this.numberOfPlayers = numberOfPlayers;
		this.doubleKO = doubleKO;
	}
        
        public KoSystem(DatabaseConnector dc,String name,int numberOfPlayers,boolean doubleKO) throws SQLException{
            super(name);
            this.dc = dc;
            this.numberOfPlayers = numberOfPlayers;
            this.doubleKO = doubleKO;
            this.id = dc.insert(String.format("INSERT INTO KoSystem(DoubleKO, NumberOfPlayers) VALUES (%s, %d)",doubleKO,numberOfPlayers));

        }
	
	// getters and setters
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public boolean isDoubleKO() {
		return doubleKO;
	}
	public void setDoubleKO(boolean doubleKO) {
		this.doubleKO = doubleKO;
	}

}
