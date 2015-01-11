package Data.LiveClasses;

public class KoSystem extends TournamentSystem{
	private int numberOfPlayers;
	private boolean doubleKO;
        private int id;
	public KoSystem(String name, int numberOfPlayers, boolean doubleKO) {
		super(name);
		this.numberOfPlayers = numberOfPlayers;
		this.doubleKO = doubleKO;
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
