package Data.LiveClasses;

public abstract class TournamentSystem {
	private String name;
	private int id;
	public TournamentSystem(String name) {
		this.name = name;
	}

	// getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
        
        public int getId(){
            return this.id;
        }

}
