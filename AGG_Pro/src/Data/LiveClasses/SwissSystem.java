package Data.LiveClasses;

public class SwissSystem extends TournamentSystem{
	private boolean doubleKO;
	private int numberOfPlayers;

	public SwissSystem(String name, boolean doubleKO, int numberOfPlayers) {
		super(name);
		this.doubleKO = doubleKO;
		this.numberOfPlayers = numberOfPlayers;
	}

	// getters and setters

	public boolean isDoubleKO() {
		return doubleKO;
	}

	public void setDoubleKO(boolean doubleKO) {
		this.doubleKO = doubleKO;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

}
