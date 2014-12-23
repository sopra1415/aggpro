package liveclasses;

public abstract class TournamentSystem {
	private String name;
	
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

}
