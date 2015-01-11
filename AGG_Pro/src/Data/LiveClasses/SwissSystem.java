package Data.LiveClasses;

public class SwissSystem extends TournamentSystem{
	private int numberOfPlayersAfterCut;
	private int numberOfRounds;
        private int id;

   
	public SwissSystem(String name, int numberOfPlayersAfterCut,
			int numberOfRounds) {
		super(name);
		this.numberOfPlayersAfterCut = numberOfPlayersAfterCut;
		this.numberOfRounds = numberOfRounds;
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
