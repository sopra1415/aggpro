package Data.LiveClasses;

public class KoSystem extends TournamentSystem{
	private int numberOfPlayers;
	private boolean doubleKO;
	public KoSystem(String name, int numberOfPlayers, boolean doubleKO, int id) {
		super(name, id);
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
