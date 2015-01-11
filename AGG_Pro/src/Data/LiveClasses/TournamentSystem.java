package Data.LiveClasses;

public abstract class TournamentSystem {
	private String name;
	private int id;
	public TournamentSystem(String name, int id) {
		this.name = name;
                this.id = id;
	}

	// getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
        
        public int getId(){
            return id;
        }

}
