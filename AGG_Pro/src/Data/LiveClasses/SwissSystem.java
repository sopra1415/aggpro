package Data.LiveClasses;

public class SwissSystem extends TournamentSystem{
	private int numberOfPlayersAfterCut;
	private int numberOfRounds;
        private int cut;

   
	public SwissSystem(String name, int numberOfPlayersAfterCut,
			int numberOfRounds, int id, int cut) {
		super(name, id);
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
        
        public int getCut() {
        return cut;
        }   
	
	





}
